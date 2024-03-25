package com.example.basictaskapplication

class Task(val titulo: String, val descricao: String, val status: String) {
    override fun toString(): String {
        return "Task(titulo='$titulo', descricao='$descricao', status='$status')"
    }
}
