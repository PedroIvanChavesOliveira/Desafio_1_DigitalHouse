import java.time.LocalDate

class ProfessorTitular(override var nome: String,
                       override var sobrenome: String,
                       override var tempoDeCasa: LocalDate,
                       override var codigoProfessor: String,
                       var especialidade: String
): Professor