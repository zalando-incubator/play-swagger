package de.zalando.model

import de.zalando.apifirst.Application._
import de.zalando.apifirst.Domain._
import de.zalando.apifirst.Http._
import de.zalando.apifirst.Hypermedia._
import de.zalando.apifirst.{ParameterPlace, Security}
import de.zalando.apifirst.naming._
//noinspection ScalaStyle
object simple_petstore_api_yaml extends WithModel {
 
 val types = Map[Reference, Type](
	Reference("⌿definitions⌿errorModel") → 
		TypeDef(Reference("⌿definitions⌿errorModel"), 
			Seq(
					Field(Reference("⌿definitions⌿errorModel⌿code"), Intgr(new TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿errorModel⌿message"), Str(None, new TypeMeta(None, List())))
			), new TypeMeta(None, List())),
	Reference("⌿definitions⌿pet") → 
		TypeDef(Reference("⌿definitions⌿pet"), 
			Seq(
					Field(Reference("⌿definitions⌿pet⌿id"), Lng(new TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿pet⌿name"), Str(None, new TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿pet⌿tag"), TypeRef(Reference("⌿definitions⌿newPet⌿tag")))
			), new TypeMeta(None, List())),
	Reference("⌿definitions⌿newPet") → 
		TypeDef(Reference("⌿definitions⌿newPet"), 
			Seq(
					Field(Reference("⌿definitions⌿newPet⌿name"), Str(None, new TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿newPet⌿id"), TypeRef(Reference("⌿definitions⌿newPet⌿id"))),
					Field(Reference("⌿definitions⌿newPet⌿tag"), TypeRef(Reference("⌿definitions⌿newPet⌿tag")))
			), new TypeMeta(None, List())),
	Reference("⌿definitions⌿newPet⌿tag") → 
		Opt(Str(None, new TypeMeta(None, List())), new TypeMeta(None, List())),
	Reference("⌿definitions⌿newPet⌿id") → 
		Opt(Lng(new TypeMeta(None, List())), new TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/{id}⌿delete⌿id") → 
		Lng(new TypeMeta(None, List())),
	Reference("⌿paths⌿/pets⌿get⌿limit") → 
		Opt(Intgr(new TypeMeta(None, List("""max(200.toInt, false)""", """min(1.toInt, false)"""))), new TypeMeta(None, List())),
	Reference("⌿paths⌿/pets⌿get⌿tags") → 
		Opt(TypeRef(Reference("⌿paths⌿/pets⌿get⌿tags⌿Opt")), new TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/{id}⌿delete⌿responses⌿204") → 
		Null(new TypeMeta(None, List())),
	Reference("⌿paths⌿/pets⌿get⌿tags⌿Opt") → 
		Arr(Str(None, new TypeMeta(None, List())), new TypeMeta(None, List()), "csv"),
	Reference("⌿paths⌿/pets⌿get⌿responses⌿200") → 
		ArrResult(TypeRef(Reference("⌿definitions⌿pet")), new TypeMeta(None, List()))
) 
 
 val parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/pets/{id}⌿get⌿id")) → Parameter("id", Lng(new TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/pets/{id}⌿delete⌿id")) → Parameter("id", Lng(new TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/pets⌿post⌿pet")) → Parameter("pet", TypeRef(Reference("⌿definitions⌿newPet")), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/pets⌿get⌿limit")) → Parameter("limit", TypeRef(Reference("⌿paths⌿/pets⌿get⌿limit")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/pets⌿get⌿tags")) → Parameter("tags", TypeRef(Reference("⌿paths⌿/pets⌿get⌿tags")), None, None, ".+", encode = true, ParameterPlace.withName("query"))
) 
val basePath: String = "/api" 
val discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
val securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
val stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
val calls: Seq[ApiCall] = Seq(
	ApiCall(GET, Path(Reference("⌿pets")), 
		HandlerCall(
			"admin",
			"Dashboard",
			instantiate = false,
			"methodLevel",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/pets⌿get⌿tags")),
				ParameterRef(Reference("⌿paths⌿/pets⌿get⌿limit"))
				)
			), 
		Set(MimeType("application/json")), 
		Set(MimeType("application/json"), MimeType("application/xml"), MimeType("text/xml"), MimeType("text/html")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/pets⌿get⌿responses⌿200"))
		), Some(	ParameterRef(Reference("⌿definitions⌿errorModel")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(POST, Path(Reference("⌿pets")), 
		HandlerCall(
			"simple.petstore.api.yaml",
			"SimplePetstoreApiYaml",
			instantiate = false,
			"addPet",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/pets⌿post⌿pet"))
				)
			), 
		Set(MimeType("application/json")), 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿definitions⌿pet"))
		), Some(	ParameterRef(Reference("⌿definitions⌿errorModel")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿pets⌿{id}")), 
		HandlerCall(
			"admin",
			"Dashboard",
			instantiate = false,
			"pathLevelGet",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/pets/{id}⌿get⌿id"))
				)
			), 
		Set(MimeType("application/json")), 
		Set(MimeType("application/json"), MimeType("application/xml"), MimeType("text/xml"), MimeType("text/html")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿definitions⌿pet"))
		), Some(	ParameterRef(Reference("⌿definitions⌿errorModel")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(DELETE, Path(Reference("⌿pets⌿{id}")), 
		HandlerCall(
			"admin",
			"Dashboard",
			instantiate = false,
			"pathLevelDelete",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/pets/{id}⌿delete⌿id"))
				)
			), 
		Set(MimeType("application/json")), 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			204 -> ParameterRef(Reference("⌿paths⌿/pets/{id}⌿delete⌿responses⌿204"))
		), Some(	ParameterRef(Reference("⌿definitions⌿errorModel")))), 
		StateResponseInfo(
				Map[Int, State](
					204 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]))

val packageName: Option[String] = None

val model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 