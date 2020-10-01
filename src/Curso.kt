class Curso(var nomeDoCurso: String, var codigoCurso: String, var maximoAlunos: Int) {

    var listaAlunosMatriculados: MutableList<Aluno> = mutableListOf()
    var contadorAlunos = 1
    var professorTitular: ProfessorTitular? = null
    var professorAdjunto: ProfessorAdjunto? = null


    fun adicionarUmAluno(umAluno: Aluno?): Boolean {
        umAluno?.let {
            return if(contadorAlunos > maximoAlunos) {
                println("Não há vagas no momento")
                false
            }else{
                if(listaAlunosMatriculados.contains(umAluno)){
                    listaAlunosMatriculados.add(umAluno)
                }else {
                    contadorAlunos++
                    listaAlunosMatriculados.add(umAluno)
                }
                true
            }
        }?: return false
    }

    fun excluirAluno(umAluno: Aluno?) {
        val listaAux: MutableList<Aluno> = mutableListOf()

        for(i in listaAlunosMatriculados) {
            listaAux.add(i)
        }
        umAluno?.let {
            listaAlunosMatriculados.forEach {
                if(it.codigoAluno == umAluno.codigoAluno) {
                    var count = 0
                    for (i in listaAlunosMatriculados) {
                        if(listaAlunosMatriculados.contains(umAluno)) {
                            count++
                        }
                    }
                    for (i in 0..count) {
                        listaAux.remove(umAluno)
                    }
                    contadorAlunos--
                }
            }
            listaAlunosMatriculados = listaAux
        }
    }
}