package com.example.ado1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sh = getSharedPreferences("shprodutos", MODE_PRIVATE)


        btnLimpar.setOnClickListener { v: View ->
            txtProduto.text.clear()
            txtCusto.text.clear()
            txtVenda.text.clear()
            txtSoma.text.clear()
        }

        btnCadastrar.setOnClickListener { v: View ->
            if (txtProduto.text.isNotEmpty() && txtCusto.text.isNotEmpty() && txtVenda.text.isNotEmpty()) {
                sh.edit().putString(txtProduto.text.toString()+"produto", txtProduto.text.toString()).apply()
                sh.edit().putString(txtProduto.text.toString()+"custo", txtCusto.text.toString()).apply()
                sh.edit().putString(txtProduto.text.toString()+"venda", txtVenda.text.toString()).apply()
                Toast.makeText(this, "Produto Cadastrado", Toast.LENGTH_SHORT).show()

                txtProduto.text.clear()
                txtCusto.text.clear()
                txtVenda.text.clear()
                txtSoma.text.clear()
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnPesquisar.setOnClickListener { v: View ->
            if (txtPesquisar.text.isNotEmpty()) {
                var produto = sh.getString(txtPesquisar.text.toString() + "produto","")
                var custo = sh.getString(txtPesquisar.text.toString() + "custo","")
                var venda = sh.getString(txtPesquisar.text.toString() + "venda","")

                var soma = venda!!.toFloat() - custo!!.toFloat()
                if (produto!!.isNotEmpty() && custo!!.isNotEmpty() && venda!!.isNotEmpty()) {
                    txtProduto.setText(sh.getString(txtPesquisar.text.toString() + "produto",""))
                    txtCusto.setText(sh.getString(txtPesquisar.text.toString() + "custo",""))
                    txtVenda.setText(sh.getString(txtPesquisar.text.toString() + "venda",""))

                    if(soma > 0){
                        lblSoma.setText("Lucro")
                        txtSoma.setText(soma.toString())
                    }else if(soma < 0){
                        lblSoma.setText("Prejuizo")
                        txtSoma.setText(soma.toString())
                    }
                } else {
                    Toast.makeText(this, "Produto vazio ou Inexistente", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this,"Nome para pesquisa vazio", Toast.LENGTH_SHORT).show()
            }
        }
    }
}