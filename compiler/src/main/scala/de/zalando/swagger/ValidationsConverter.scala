package de.zalando.swagger

import de.zalando.swagger.strictModel._

/**
  * @author  slasch 
  * @since   16.10.2015.
  */

object ValidationsConverter {

  def toValidations(s: Schema[_]): Seq[String] = validationsPF(s)

  def toValidations[T, CF](bp: BodyParameter[T]): Seq[String] = validationsPF(bp)

  def toValidations[T, CF](bp: NonBodyParameter[T]): Seq[String] = validationsPF(bp)

  def toValidations(nb: NonBodyParameterCommons[_, _]): Seq[String] = validationsPF(nb)

  def toValidations[T, CF](bp: PrimitivesItems[T]): Seq[String] = validationsPF(bp)

  private val allValidations = Seq[PartialFunction[ValidationBase, Seq[String]]](
    { case v: ArrayValidation[_] => toArrayValidations(v) },
    { case v: StringValidation => toStringValidations(v) },
    { case n: NumberValidation[_] => toNumberValidations(n) },
    { case o: ObjectValidation => toObjectValidations(o) }
  )

  private def validationsPF[CF, T](b: ValidationBase): Seq[String] = {
    val default: ValidationBase => Seq[String] = _ => Nil
    allValidations flatMap { v => v.applyOrElse(b, default) }
  }

  def toArrayValidations[T](a: ArrayValidation[T]): Seq[String] =
    Seq(
      ifDefined(a.maxItems, s"maxItems(${a.maxItems})" ),
      ifDefined(a.minItems, s"minItems(${a.minItems})" ),
      ifDefined(a.uniqueItems, s"uniqueItems(${a.uniqueItems})"),
      ifDefined(a.enum, s"enum(${a.enum.mkString(",")})" ) // TODO what if parameters contain commas ?
    ).flatten

  private def toStringValidations(p: StringValidation): Seq[String] = {
    val format = p match {
      case c: NonBodyParameterCommons[_, _] => Some(c.format)
      case _ => None
    }
    val emailConstraint: Option[String] = if (format.exists(_ == "email")) Some("emailAddress") else None
    Seq(
      ifDefined(p.maxLength, s"maxLength(${p.maxLength.get})"),
      ifDefined(p.minLength, s"minLength(${p.minLength.get})"),
      p.pattern map { p => s"""pattern("$p".r)""" },
      emailConstraint
    ).flatten
  }

  private def toNumberValidations[T](p: NumberValidation[T]): Seq[String] = {
    val strictMax = p.exclusiveMaximum.getOrElse(false)
    val strictMin = p.exclusiveMinimum.getOrElse(false)
    Seq(
      ifDefined(p.maximum, s"max(${p.maximum.get}, $strictMax)"),
      ifDefined(p.minimum, s"min(${p.minimum.get}, $strictMin)"),
      ifDefined(p.multipleOf, s"multipleOf(${p.multipleOf.get})")
    ).flatten
  }

  private def toObjectValidations(p: ObjectValidation): Seq[String] =
    Seq(
      ifDefined(p.maxProperties, s"maxProperties(${p.maxProperties.get})"),
      ifDefined(p.minProperties, s"minProperties(${p.minProperties.get})")
    ).flatten

  def ifNot0(check:Int, result: String): Option[String] = if (check != 0) Some(result) else None

  private def ifDefined[T](validation: Option[T], result: => String) = validation map { _ => result }
}
