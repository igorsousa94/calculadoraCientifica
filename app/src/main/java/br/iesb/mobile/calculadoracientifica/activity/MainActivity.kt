package br.iesb.mobile.calculadoracientifica.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.iesb.mobile.calculadoracientifica.R
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.Expression
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numeros
        tvNumber0.setOnClickListener { montaHistorico(true, "0") }
        tvNumber1.setOnClickListener { montaHistorico(true, "1") }
        tvNumber2.setOnClickListener { montaHistorico(true, "2") }
        tvNumber3.setOnClickListener { montaHistorico(true, "3") }
        tvNumber4.setOnClickListener { montaHistorico(true, "4") }
        tvNumber5.setOnClickListener { montaHistorico(true, "5") }
        tvNumber6.setOnClickListener { montaHistorico(true, "6") }
        tvNumber7.setOnClickListener { montaHistorico(true, "7") }
        tvNumber8.setOnClickListener { montaHistorico(true, "8") }
        tvNumber9.setOnClickListener { montaHistorico(true, "9") }
        tvDecimal.setOnClickListener { montaHistorico(true, ".") }

        //Operacoes
        tvSoma.setOnClickListener { montaHistorico(false, "+") }
        tvSubtracao.setOnClickListener { montaHistorico(false, "-") }
        tvMultiplicacao.setOnClickListener { montaHistorico(false, "*") }
        tvDivisao.setOnClickListener { montaHistorico(false, "/") }
        tvAbrirParenteses.setOnClickListener { montaHistorico(false, "(") }
        tvFecharParenteses.setOnClickListener { montaHistorico(false, ")") }
        tvPotencia.setOnClickListener { montaHistorico(false, "^") }
        tvFatorial.setOnClickListener { montaHistorico(false, "!") }
        tvQuadrado.setOnClickListener { montaHistorico(false, "^2") }
        tvRaizQuadrada.setOnClickListener { montaHistorico(false, "sqrt(") }
        tvLog.setOnClickListener { montaHistorico(false, "log10(") }
        tvLogaritmoNatural.setOnClickListener { montaHistorico(false, "ln(") }
        tvSeno.setOnClickListener { montaHistorico(false, "sin(") }
        tvCoseno.setOnClickListener { montaHistorico(false, "cos(") }
        tvTangente.setOnClickListener { montaHistorico(false, "tan(") }
        tvSenoInverso.setOnClickListener { montaHistorico(false, "cosec(") }
        tvCosenoInverso.setOnClickListener { montaHistorico(false, "sec(") }
        tvTangenteInverso.setOnClickListener { montaHistorico(false, "cot(") }
        tvModulo.setOnClickListener { montaHistorico(false, "abs(") }
        tvInverterNumero.setOnClickListener { montaHistorico(false, "1/") }
        tvPositivoNegativo.setOnClickListener{ montaHistorico(false, "*-1") }

        //Funcoes de tela
        tvZerar.setOnClickListener {limpar()}
        tvDeletar.setOnClickListener { deletar()}
        tvCalcular.setOnClickListener {calcular()}
    }

    fun montaHistorico(numero: Boolean, entrada: String) {
        if (numero) {
            tvResultado.text = ""
            tvHistoricoOperacao.append(entrada)
        } else {
            if(tvHistoricoOperacao.text.toString().length > 1){
                var ultimaLetra = tvHistoricoOperacao.text.last()
                if ( !ultimaLetra.toString().equals("(") && !ultimaLetra.toString().equals(")") && !ultimaLetra.isDigit() ) {
                    tvHistoricoOperacao.text = tvHistoricoOperacao.text.toString().substring(0, tvHistoricoOperacao.text.lastIndex);
                }
            }
            preencheHistorico(entrada)
        }
    }

    fun preencheHistorico(entrada: String){
        tvResultado.text = ""
        tvHistoricoOperacao.append(tvResultado.text)
        tvHistoricoOperacao.append(entrada)
    }

    fun limpar(){
        tvHistoricoOperacao.text = "";
        tvResultado.text = "0";
    }

    fun deletar(){
        if(tvHistoricoOperacao.text.lastIndex != -1){
            tvHistoricoOperacao.text = tvHistoricoOperacao.text.toString().substring(0, tvHistoricoOperacao.text.lastIndex);
        } else {
            tvHistoricoOperacao.text = ""
        }
    }

    fun calcular(){
        try {
            val input = Expression(tvHistoricoOperacao.text.toString())
            if(input.calculate().isNaN()){
                tvResultado.text = "Resultado inválido"
            } else {
                tvResultado.text = input.calculate().toString()
            }
            var operacao = tvHistoricoOperacao.text.toString()
            var result = tvResultado.text.toString()
            tvHistoricoTudo.append("\n"+operacao+"="+result)
            tvHistoricoOperacao.text = ""
        }catch (e:Exception){
            Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_LONG).show()
        }
    }

}