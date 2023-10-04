package com.grud.mobilbimestre.ui

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.grud.mobilbimestre.R

class AdapterLista(
    private  val context: Context,
    private val MinhaLista: MutableList<String>,
    val itemClicado: (String) -> Unit,

    ) : RecyclerView.Adapter<AdapterLista.MyView>(){


    //RESPONSAVEL POR CRIAR O LAYOUT POR CADA LINHA QUE TEMOSS
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_list,parent,false)
        return MyView(itemView)

    }
    //IMPRIME AS INFORMAÇÕES

    override  fun onBindViewHolder(holder: MyView, position: Int) {
        val name = MinhaLista[position]  //variavel name pega lista e suas posições
        holder.txtName.text = name
        //evento click
        //itemView pois quero pegar a área toda, se fosse em cima apenas do nome seria referência apenas do nome.  -ex:txtName

        holder.itemView.setOnClickListener {itemClicado(name)} //ao clicar na a´rea total do item o name vai aparecer

        // Configurar o clique no botão "Editar"
        holder.buttonEdit.setOnClickListener {
            showEditDialog(name, position)
        }


    }


    //RETORNA O TAMANHO DA FONTE DE DADOS, PARA SABER VALOR D ELINHAS
    override fun getItemCount() = MinhaLista.size

    inner class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.itemNome)
        val buttonEdit: Button = itemView.findViewById(R.id.buttonEdit)
        val buttonExcluir: Button = itemView.findViewById(R.id.buttonExcluir)

        init {
            buttonExcluir.setOnClickListener {
                // Chame a função para excluir o item da lista
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    MinhaLista.removeAt(position)
                    notifyItemRemoved(position)

                    // Salve a lista atualizada no SharedPreferences
                    saveListToSharedPreferences(MinhaLista)
                }
            }
        }
    }
    private fun showEditDialog(name: String, position: Int) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setTitle("Editar Nome")

        val editText = EditText(context)
        editText.setText(name)
        dialogBuilder.setView(editText)


        dialogBuilder.setPositiveButton("Salvar") { _, _ ->
            // Atualize o item com o novo valor
            MinhaLista[position] = editText.text.toString()
            notifyItemChanged(position)

            // Salve a lista atualizada no SharedPreferences
            saveListToSharedPreferences(MinhaLista)
        }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }.setNeutralButton("Excluir") { _, _ ->
                // Exclua o item da lista
                MinhaLista.removeAt(position)
                notifyItemRemoved(position)

                // Salve a lista atualizada no SharedPreferences
                saveListToSharedPreferences(MinhaLista)
            }

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    private fun saveListToSharedPreferences(lista: List<String>) {
        val sharedPreferences = context.getSharedPreferences("MinhaListaSharedPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Converte a lista em uma única string separada por vírgulas
        val listaString = lista.joinToString(",")

        editor.putString("MinhaListaKey", listaString)
        editor.apply()
    }




    private fun showAddDialog01() {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setTitle("Adicionar Nome")

        val editText = EditText(context)
        dialogBuilder.setView(editText)

        dialogBuilder.setPositiveButton("Adicionar") { _, _ ->
            val newName = editText.text.toString()

            // Verifique se o novo nome não está vazio
            if (newName.isNotEmpty()) {
                // Adicione o novo nome à lista
                MinhaLista.add(newName)

                // Notifique o RecyclerView sobre a mudança
                notifyItemInserted(MinhaLista.size - 1)

                // Salve a lista atualizada no SharedPreferences
                saveListToSharedPreferences01(MinhaLista)
            } else {
                Toast.makeText(context, "Digite um nome válido", Toast.LENGTH_SHORT).show()
            }
        }
        dialogBuilder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    private fun saveListToSharedPreferences01(lista: List<String>) {
        val sharedPreferences = context.getSharedPreferences("MinhaListaSharedPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Converte a lista em uma única string separada por vírgulas
        val listaString = lista.joinToString(",")

        editor.putString("MinhaListaKey", listaString)
        editor.apply()
    }
}

