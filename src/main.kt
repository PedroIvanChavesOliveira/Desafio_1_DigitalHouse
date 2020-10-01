import java.time.format.DateTimeFormatter

fun main() {
    val digitalHouse = DigitalHouseManager()

//  TESTES
    digitalHouse.registrarCurso("Desenvolvimento Mobile Android", "DEVANDROID", 5)
    digitalHouse.registrarCurso("Desenvolvimento Mobile iOS", "DEVIOS", 5)

    digitalHouse.excluirCurso("DEVIOS")

    println("---------------------------------------")
    digitalHouse.registrarProfessorTitular("Roberto", "Roth", "T001",  "Android")
    digitalHouse.registrarProfessorTitular("Cleber", "Costa", "T002",  "iOS")
    digitalHouse.registrarProfessorAdjunto("Tomaz", "Sapori", "A001", 20)
    digitalHouse.registrarProfessorAdjunto("João", "Oliveira", "A002", 40)

    digitalHouse.excluirProfessor("A002")
    digitalHouse.excluirProfessor("T002")
    println("---------------------------------------")
    digitalHouse.registrarAluno("Pedro", "Chaves", "ALUNO001")
    digitalHouse.registrarAluno("Carol", "Andrade", "ALUNO002")
    digitalHouse.registrarAluno("Joao", "Ferreira", "ALUNO003")
    digitalHouse.registrarAluno("Ricardo", "Castro", "ALUNO004")
    digitalHouse.registrarAluno("Maria Luiza", "Oliveira", "ALUNO005")
    digitalHouse.registrarAluno("Claudio", "Gutierrez", "ALUNO006")
    digitalHouse.registrarAluno("Pablo", "Pinheiro", "ALUNO007")
    digitalHouse.registrarAluno("Antônio", "Silva", "ALUNO008")
    digitalHouse.registrarAluno("Jefferson", "Antunes", "ALUNO009")
    digitalHouse.registrarAluno("Camila", "Cerqueira", "ALUNO010")
    digitalHouse.registrarAluno("Camila", "Cerqueira", "ALUNO010")

    println("---------------------------------------")

    digitalHouse.matricularAluno("ALUNO001", "DEVANDROID")
    digitalHouse.matricularAluno("ALUNO001", "DEVANDROID")
    digitalHouse.matricularAluno("ALUNO002", "DEVANDROID")
    digitalHouse.matricularAluno("ALUNO003", "DEVANDROID")
    digitalHouse.matricularAluno("ALUNO004", "DEVANDROID")
    digitalHouse.matricularAluno("ALUNO005", "DEVANDROID")
    digitalHouse.matricularAluno("ALUNO008", "DEVANDROID")

    println("---------------------------------------")
    digitalHouse.matricularAluno("ALUNO007", "DEVIOS")
    digitalHouse.matricularAluno("ALUNO008", "DEVIOS")
    digitalHouse.matricularAluno("ALUNO009", "DEVIOS")
    digitalHouse.matricularAluno("ALUNO010", "DEVIOS")
    digitalHouse.matricularAluno("ALUNO006", "DEVIOS")
    digitalHouse.matricularAluno("ALUNO001", "DEVIOS")

    println("---------------------------------------")
    digitalHouse.alocarProfessores("DEVANDROID", "T001", "A001")
    digitalHouse.alocarProfessores("DEVIOS", "T002", "A002")

    println(digitalHouse.listaCursos)
    for(i in digitalHouse.listaCursos) {
        println("${i.value.professorAdjunto?.nome} e ${i.value.professorTitular?.nome}")
    }
    println("---------------------------------------")

    //SAÍDA DE DADOS
    println("Dados do Curso de ${digitalHouse.listaCursos["DEVANDROID"]?.nomeDoCurso}")
    println("---------------------------------------")
    digitalHouse.listaMatriculas.forEach {
        if(it.value.curso?.codigoCurso == digitalHouse.listaCursos["DEVANDROID"]?.codigoCurso) {
            println("${it.value.aluno?.nome} ${it.value.aluno?.sobrenome} - ${it.value.aluno?.codigoAluno} Matrícula: ${it.key} ")
        }
    }
    println("---------------------------------------")
    digitalHouse.listaCursos["DEVANDROID"]?.professorTitular?.let {
        println("${it.nome} ${it.sobrenome} - ${it.codigoProfessor}\n" +
                "Admissão: ${it.tempoDeCasa?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}\nEspecialidade: ${it.especialidade}")
    }
    println("---------------------------------------")
    digitalHouse.listaCursos["DEVANDROID"]?.professorAdjunto?.let {
        println("${it.nome} ${it.sobrenome} - ${it.codigoProfessor}\n" +
                "Admissão: ${it.tempoDeCasa?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}\nTempo de Monitoria Disponível: ${it.tempoDeMonitoria} horas")
    }
    println("---------------------------------------")

    println("Dados do Curso de ${digitalHouse.listaCursos["DEVIOS"]?.nomeDoCurso}")
    println("---------------------------------------")
    digitalHouse.listaMatriculas.forEach {
        if(it.value.curso?.codigoCurso == digitalHouse.listaCursos["DEVIOS"]?.codigoCurso) {
            println("${it.value.aluno?.nome} ${it.value.aluno?.sobrenome} - ${it.value.aluno?.codigoAluno} Matrícula: ${it.key} ")
        }
    }
    println("---------------------------------------")
    digitalHouse.listaCursos["DEVIOS"]?.professorTitular?.let {
        println("${it.nome} ${it.sobrenome} - ${it.codigoProfessor}\n" +
                "Admissão: ${it.tempoDeCasa?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}\nEspecialidade: ${it.especialidade}")
    }
    println("---------------------------------------")
    digitalHouse.listaCursos["DEVIOS"]?.professorAdjunto?.let {
        println("${it.nome} ${it.sobrenome} - ${it.codigoProfessor}\n" +
                "Admissão: ${it.tempoDeCasa?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}\nTempo de Monitoria Disponível: ${it.tempoDeMonitoria} horas")
    }
    println("---------------------------------------")
}