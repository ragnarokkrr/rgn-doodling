import org.ragna.nomin.from.*
import org.ragna.nomin.to.*

mappingFor a: Person, b: Employee
a.name = b.name
a.lastName = b.last
b.details.birth = a.birthDate
a.children = b.details.kids
//hint a: List[Child], b: Set[Kid]


a.strDate = b.details.birth
dateFormat "dd-MM-yyyy"

a.gender = b.details.sex
simple([a: Gender.MALE, b: true], [a: Gender.FEMALE, b: false])
