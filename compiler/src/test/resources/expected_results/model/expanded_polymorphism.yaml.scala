package expanded_polymorphism
package object yaml {
import de.zalando.play.controllers.ArrayWrapper
import de.zalando.play.controllers.PlayPathBindables
type PetsIdDeleteResponses204 = Null

    type PetsIdDeleteId = Long

    type PetsGetLimit = Option[Int]

    type NewPetTag = Option[String]

    type PetsGetTagsOpt = ArrayWrapper[String]

    type PetsGetResponses200 = ArrayWrapper[Pet]

    type PetsGetTags = Option[PetsGetTagsOpt]

    case class NewPet(name: String, tag: NewPetTag) 

    case class Pet(name: String, tag: NewPetTag, id: Long) 

    case class Error(code: Int, message: String) 

    


    
    
    implicit val bindable_OptionIntQuery = PlayPathBindables.createOptionQueryBindable[Int]
    implicit val bindable_OptionPetsGetTagsOptQuery = PlayPathBindables.createOptionQueryBindable[PetsGetTagsOpt]
    implicit val bindable_ArrayWrapperStringQuery = PlayPathBindables.createArrayWrapperQueryBindable[String]("csv")
    }