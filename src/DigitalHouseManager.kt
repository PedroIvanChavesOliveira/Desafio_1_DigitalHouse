import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter

class DigitalHouseManager {
     var listaAlunos: MutableMap<String, Aluno> = mutableMapOf()
     var listaProfessores: MutableMap<String, Professor> = mutableMapOf()
     var listaCursos: MutableMap<String, Curso> = mutableMapOf()
     var listaMatriculas: MutableMap<Int, Matricula> = mutableMapOf()
     var numeroMatricula = 10001

    fun registrarCurso(nome: String?, codigoCurso: String?, maximoAlunos: Int?) {
        nome?.let {
            codigoCurso?.let {
                maximoAlunos?.let {
                    val curso = Curso(nome, codigoCurso, maximoAlunos)

                    listaCursos[codigoCurso] = curso
                    println("Novo Curso Registrado: $nome")
                }?: println("Máximo de alunos null")
            }?: println("Código Curso null")
        }?: println("Nome null")
    }

    fun excluirCurso(codigoCurso: String?) {
        codigoCurso?.let {
            listaCursos.forEach {
                if (it.key == codigoCurso) {
                    listaCursos.remove(codigoCurso)
                    println("Curso Removido: ${it.value.nomeDoCurso}")
                }
            }
        }?: println("Excuindo null")
    }

    fun registrarProfessorAdjunto(nome: String?, sobrenome: String?, codigoProfessor: String?, quantidadeDeHoras: Int?) {
        val random = (30..1825).shuffled().first()

        nome?.let {
            sobrenome?.let {
                codigoProfessor?.let {
                    quantidadeDeHoras?.let {
                        val professor = ProfessorAdjunto(nome, sobrenome, LocalDate.now(), codigoProfessor, quantidadeDeHoras)

                        listaProfessores[codigoProfessor] = professor
                        professor.tempoDeCasa = LocalDate.now().minusDays(random.toLong())

                        println("${professor.nome} ${professor.sobrenome} - Registrado na DH!")
                    }?: println("Quantidade de horas null")
                }?: println("Código Professor null")
            }?: println("Sobrenome null")
        }?: println("Nome null")
    }

    fun registrarProfessorTitular(nome: String?, sobrenome: String?, codigoProfessor: String?, especialidade: String?) {
        val random = (30..1825).shuffled().first()

        nome?.let {
            sobrenome?.let {
                codigoProfessor?.let {
                    especialidade?.let {
                        val professor = ProfessorTitular(nome, sobrenome, LocalDate.now(), codigoProfessor, especialidade)

                        listaProfessores[codigoProfessor] = professor
                        professor.tempoDeCasa = LocalDate.now().minusDays(random.toLong())

                        println("${professor.nome} ${professor.sobrenome} - Registrado na DH!")
                    }?: println("Especialidade null")
                }?: println("Codigo Professor null")
            }?: println("Sobrenome null")
        }?: println("Nome null")
    }

    fun excluirProfessor(codigoProfessor: String?) {
        val listaAux: MutableMap<String, Professor> = mutableMapOf()

        for(i in listaProfessores) {
            listaAux[i.key] = i.value
        }

        codigoProfessor?.let {
            listaProfessores.forEach {
                if (it.value.codigoProfessor == codigoProfessor) {
                    listaAux.remove(codigoProfessor)
                    val period = Period.between(it.value.tempoDeCasa, LocalDate.now())

                    println("${it.value.nome} trabalhou durante ${period.days} dia(s), ${period.months} mes(es) e ${period.years} ano(s)")
                }
            }
            listaProfessores = listaAux
        }?: println("Professor null")

    }

    fun registrarAluno(nome: String?, sobrenome: String?, codigoAluno: String?) {
        nome?.let {
            sobrenome?.let {
                codigoAluno?.let {
                    val aluno = Aluno(nome, sobrenome, codigoAluno)

                    if(listaAlunos.containsKey(codigoAluno)) {
                        println("Aluno ${aluno.nome} ${aluno.sobrenome} já cadastrado")
                    }else {
                        listaAlunos[codigoAluno] = aluno
                        println("Aluno ${aluno.nome} ${aluno.sobrenome} registrado!!")
                    }
                }?: println("Código do Aluno null")
            }?: println("Sobrenome null")
        }?: println("Nome null")
    }

    fun matricularAluno(codigoAluno: String?, codigoCurso: String?) {
        var cadastro: Boolean? = false
        val curso: Curso? = listaCursos[codigoCurso]
        val aluno: Aluno? = listaAlunos[codigoAluno]

        codigoAluno?.let {
            codigoCurso?.let {
                if(listaAlunos.containsKey(codigoAluno) && listaCursos.containsKey(codigoCurso)) {
                    cadastro = curso?.adicionarUmAluno(aluno)
                }else {
                    println("Aluno e/ou curso não encontrado")
                }
                for (i in listaMatriculas.values) {
                    if(i.aluno == aluno && i.curso == curso) {
                        cadastro = false
                        println("Aluno já matriculado neste curso!")
                    }else if(i.aluno == aluno && i.curso != curso){
                        cadastro = curso?.adicionarUmAluno(aluno)
                    }
                }
                cadastro?.let {
                    if(it) {
                        listaMatriculas[numeroMatricula] = Matricula(aluno, curso, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")))
                        println("${aluno?.nome} ${aluno?.sobrenome} - Matriculado no curso ${curso?.nomeDoCurso}")
                        numeroMatricula++
                    }
                }?: println("Cadastro null")
            }?: println("Curso null")
        }?: println("Aluno null")
    }

    fun alocarProfessores(codigoCurso: String?, codigoProfessorTitular: String?, codigoProfessorAdjunto: String?) {
        var dadosProfTitular = listaProfessores[codigoProfessorTitular]
        var dadosProfAdjunto = listaProfessores[codigoProfessorAdjunto]

        codigoCurso?.let {
            codigoProfessorTitular?.let {
                codigoProfessorAdjunto?.let {
                    if(listaCursos.containsKey(codigoCurso)) {
                        if(listaProfessores.containsKey(codigoProfessorAdjunto) && listaProfessores.containsKey(codigoProfessorTitular)) {
                            for(i in listaProfessores) {
                                if(i.value.codigoProfessor == codigoProfessorAdjunto) {
                                        dadosProfAdjunto = i.value
                                }
                                if(i.value.codigoProfessor == codigoProfessorTitular) {
                                        dadosProfTitular = i.value
                                }
                            }

                            for (i in listaCursos) {
                                if (i.value.codigoCurso == codigoCurso) {
                                    i.value.professorAdjunto = dadosProfAdjunto as ProfessorAdjunto?
                                    i.value.professorTitular = dadosProfTitular as ProfessorTitular?
                                    println(
                                        "Professor Titular: ${i.value.professorTitular?.nome} ${i.value.professorTitular?.sobrenome} " +
                                                "e Professor Adjunto: ${i.value.professorAdjunto?.nome} ${i.value.professorAdjunto?.sobrenome}")
                                }
                            }
                        }else{
                            println("Professor(es) não cadastrados!")
                        }
                    }else {
                        println("Curso não cadastrado!!")
                    }
                }?: println("Professor Adjunto Null")
            }?: println("Professor Titular Null")
        }?: println("Curso Null")
    }

}
