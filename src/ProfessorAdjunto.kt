import java.time.LocalDate

class ProfessorAdjunto(override var nome: String,
                       override var sobrenome: String,
                       override var tempoDeCasa: LocalDate,
                       override var codigoProfessor: String,
                       var tempoDeMonitoria: Int
): Professor