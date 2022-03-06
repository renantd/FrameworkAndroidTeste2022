package br.framework.com.framework.arquivo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.Toast
import androidx.core.content.ContextCompat
import br.framework.com.framework.R
import br.framework.com.framework.model.Item
import br.renandev.com.validacoes.mensagem.Mensagem
import com.itextpdf.text.Document
import com.itextpdf.text.DocumentException
import com.itextpdf.text.Image
import com.itextpdf.text.PageSize
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfContentByte
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.DecimalFormat

/* Geraro documento no local: storage/emulated/0/Download */
class PDF(var context : Context ) {

    private var bfBold: BaseFont? = null
    private var bf: BaseFont? = null
    private var pageNumber = 0

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo para gerar novo Documento
    */
    fun gerarNovoDocumento(path: File, arquivo: File , itens: ArrayList<Item>) {
        val doc = Document()
        var docWriter: PdfWriter? = null
        inicializarFontes()

        try {
            docWriter = PdfWriter.getInstance(doc, FileOutputStream(path))
            doc!!.addAuthor("Renan Costa e Silva")
            doc!!.addCreationDate()
            doc!!.addProducer()
            doc!!.addCreator("Renan Costa e Silva")
            doc!!.addTitle("Comprovante de compra")
            doc!!.pageSize = PageSize.LETTER
            doc!!.open()
            val cb = docWriter.directContent
            var beginPage = true
            var y = 0
            var i = 0
            itens.forEach { e ->
                ++i
                if (beginPage) {
                    beginPage = false
                    generarLayout(doc, cb)
                    generarCabecalho(doc, cb)
                    y = 615
                }
                generarDetalhe(doc, e.nome, "Fruta - ${e.nome}", e.quantidade , e.preco , cb, i, y)
                y -= 15
                if (y < 50) {
                    imprimirNumeroPagina(cb)
                    doc!!.newPage()
                    beginPage = true
                }
            }

            imprimirNumeroPagina(cb)
            Mensagem(context).dEETL("Comprovante $arquivo gerado com sucesso em : ${path.absolutePath}")

        } catch (dex: DocumentException) {

            dex.printStackTrace()
            Toast.makeText(context , "Erro - $dex", Toast.LENGTH_SHORT).show()

        } catch (ex: java.lang.Exception) {

            ex.printStackTrace()
            Toast.makeText(context , "Erro - $ex", Toast.LENGTH_SHORT).show()

        } finally {
            doc.close()
            docWriter?.close()
        }
    }

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo para gerar layout
    */
    private fun generarLayout(doc: Document, cb: PdfContentByte) {
        try {
            cb.setLineWidth(1f)

            //Layout da caixa do cabeçalho da fatura
            cb.rectangle(420f, 700f, 150f, 60f)
            cb.moveTo(420f, 720f)
            cb.lineTo(570f, 720f)
            cb.moveTo(420f, 740f)
            cb.lineTo(570f, 740f)
            cb.moveTo(480f, 700f)
            cb.lineTo(480f, 760f)
            cb.stroke()

            //Títulos de texto da caixa de cabeçalho da fatura
            criarTitulos(cb, 422f, 743f, "Número Doc.")
            criarTitulos(cb, 422f, 723f, "CNPJ.")
            criarTitulos(cb, 422f, 703f, "Data Compra")

            //Detalhes do layout
            cb.rectangle(20f, 50f, 550f, 600f)
            cb.moveTo(20f, 630f)
            cb.lineTo(570f, 630f)
            cb.moveTo(50f, 50f)
            cb.lineTo(50f, 650f)
            cb.moveTo(150f, 50f)
            cb.lineTo(150f, 650f)
            cb.moveTo(430f, 50f)
            cb.lineTo(430f, 650f)
            cb.moveTo(500f, 50f)
            cb.lineTo(500f, 650f)
            cb.stroke()

            //Headings Detalhes do cabeçalho
            criarTitulos(cb, 22f, 633f, "Qtde")
            criarTitulos(cb, 52f, 633f, "Número do Item")
            criarTitulos(cb, 152f, 633f, "Descrição")
            criarTitulos(cb, 432f, 633f, "Preço")
            criarTitulos(cb, 502f, 633f, "Total")

            //Adiciona Image de logo
            val companyLogo = inserirLogo( context , R.drawable.logo_teste)
            companyLogo.setAbsolutePosition(35F, 685F)
            companyLogo.scalePercent(50F)
            doc.add(companyLogo)

        } catch (dex: DocumentException) {
            dex.printStackTrace()
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }
    }

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo para gerar cabeçalho
    */
    private fun generarCabecalho(doc: Document, cb: PdfContentByte) {
        try {
            criarTitulos(cb, 200f, 750f, "Sacolão XYZ")
            criarTitulos(cb, 200f, 735f, "Rua Fulano da Silva, 12345")
            criarTitulos(cb, 200f, 705f, "BH, MG - (31)")
            criarTitulos(cb, 200f, 690f, "Brasil")
            criarTitulos(cb, 482f, 743f, "ABC0001")
            criarTitulos(cb, 482f, 723f, "99.999.999/0001-99")
            criarTitulos(cb, 482f, 703f, "04/03/2022")
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }
    }

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo para gerar detalhe do Documento
    */
    private fun generarDetalhe(doc: Document , nomeFruta: String , descricao: String , quantidade: Int , preco: Double, cb: PdfContentByte, index: Int, y: Int) {
        val df = DecimalFormat("0.00")
        try {
            crirConteudo(cb, 48f, y.toFloat(), quantidade.toString() , PdfContentByte.ALIGN_RIGHT)
            crirConteudo(
                cb,
                52f,
                y.toFloat(),
                nomeFruta ,
                PdfContentByte.ALIGN_LEFT
            )
            crirConteudo(
                cb,
                152f,
                y.toFloat(),
                descricao,
                PdfContentByte.ALIGN_LEFT
            )

            crirConteudo(cb, 498f, y.toFloat(), df.format(preco), PdfContentByte.ALIGN_RIGHT)
            crirConteudo(cb, 568f, y.toFloat(), df.format(quantidade * preco) , PdfContentByte.ALIGN_RIGHT)
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }
    }

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo para criar título
    */
    private fun criarTitulos(cb: PdfContentByte, x: Float, y: Float, text: String) {
        cb.beginText()
        cb.setFontAndSize(bfBold, 8f)
        cb.setTextMatrix(x, y)
        cb.showText(text.trim { it <= ' ' })
        cb.endText()
    }

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo para imprimir número da página
    */
    private fun imprimirNumeroPagina(cb: PdfContentByte) {
        cb.beginText()
        cb.setFontAndSize(bfBold, 8f)
        cb.showTextAligned(
            PdfContentByte.ALIGN_RIGHT,
            "Página " + (pageNumber + 1),
            570f,
            25f,
            0f
        )
        cb.endText()
        pageNumber++
    }

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo para criar conteudo da página
    */
    private fun crirConteudo(cb: PdfContentByte, x: Float, y: Float, text: String, align: Int) {
        cb.beginText()
        cb.setFontAndSize(bf, 8f)
        cb.showTextAligned(align, text.trim { it <= ' ' }, x, y, 0f)
        cb.endText()
    }

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo inserir a configuração de fonte de texto
    */
    private fun inicializarFontes() {
        try {
            bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED)
            bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED)
        } catch (e: DocumentException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo inserir a logo no arquivo
    */
    fun inserirLogo(context : Context, foto: Int): Image{

        val d = ContextCompat.getDrawable(context , foto)
        val bitDw = d as BitmapDrawable
        val bmp = bitDw.bitmap
        val stream = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val image = Image.getInstance(stream.toByteArray())

        // seta posição da foto
        image.setAbsolutePosition(15f, 675f);

        //Tamanho da Imagem
        image.scaleAbsolute(100f, 100f);
        return  image

    }


}