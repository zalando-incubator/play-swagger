package string_formats

package object yaml {

    import de.zalando.play.controllers.Base64String
    import Base64String._
    import de.zalando.play.controllers.BinaryString
    import BinaryString._
    import org.joda.time.DateTime
    import org.joda.time.LocalDate

    import de.zalando.play.controllers.PlayPathBindables



    type GetBase64 = Option[Base64String]
    type GetPetId = BinaryString
    type GetDate_time = Option[DateTime]
    type GetDate = Option[LocalDate]
    type GetResponses200 = Null



    implicit val bindable_Base64Query = PlayPathBindables.queryBindableBase64String
    implicit val bindable_DateTimeQuery = PlayPathBindables.queryBindableDateTime
    implicit val bindable_LocalDateQuery = PlayPathBindables.queryBindableLocalDate
    implicit val bindable_OptionBase64StringQuery = PlayPathBindables.createOptionQueryBindable[Base64String]
    implicit val bindable_OptionDateTimeQuery = PlayPathBindables.createOptionQueryBindable[DateTime]
    implicit val bindable_OptionLocalDateQuery = PlayPathBindables.createOptionQueryBindable[LocalDate]

}
