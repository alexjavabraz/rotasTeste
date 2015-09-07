package br.com.testewm.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import javax.imageio.ImageIO;



public class UtilFunction {

    private static final String EMPTY_STRING = "";

    public static void main(String[] args) throws Exception {
//        System.out.println(encriptBase64("111222333"));
//        
//        System.out.println(descriptoBase64("Ymlhc2lucGFjaGU="));
//        
//        
//        Date hoje = calcularDia(hojeDataHoraTimesTamp(), 2);
//        
//        System.out.println(calcularDia(hoje, 2));
    	
    	
    	
//    	System.out.println(convertStringValorCifraoToBigDecimal("R$ 12,30"));
    	
//    	String data = "01/05/1982";
//    	
//    	System.out.println(UtilFunction.validarFormatoDataHora(data, "dd/MM/yyyy"));
//    	
//    	System.out.println(UtilFunction.createDate(data));
//    	
//    	BigDecimal bd = new BigDecimal(10.99);
//    	
//    	System.out.println(converteBigDecimalToLong(bd));
    	
//		
//		
//		BigDecimal retorno = calculaValorParcelado(valorPercentual, valorTotal, parcelas);
//		System.out.println("Duas vezes : " + retorno.doubleValue() + " " + (retorno.multiply(new BigDecimal(parcelas))));
//		
//		parcelas = 3;
//		BigDecimal retorno3 = calculaValorParcelado(valorPercentual, valorTotal, parcelas);
//		System.out.println("Tres vezes : " + retorno3.doubleValue()+ " " + (retorno3.multiply(new BigDecimal(parcelas))));
//		
//		parcelas = 4;
//		BigDecimal retorno4 = calculaValorParcelado(valorPercentual, valorTotal, parcelas);
//		System.out.println("Quatro vezes : " + retorno4.doubleValue()+ " " + (retorno4.multiply(new BigDecimal(parcelas))));
//		
//		parcelas = 5;
//		BigDecimal retorno5 = calculaValorParcelado(valorPercentual, valorTotal, parcelas);
//		System.out.println("5 vezes : " + retorno5.doubleValue()+ " " + (retorno5.multiply(new BigDecimal(parcelas))));
//		
//		parcelas = 6;
//		BigDecimal retorno6 = calculaValorParcelado(valorPercentual, valorTotal, parcelas);
//		System.out.println("6 vezes : " + retorno6.doubleValue()+ " " + (retorno6.multiply(new BigDecimal(parcelas))));
//		
//		parcelas = 7;
//		BigDecimal retorno7 = calculaValorParcelado(valorPercentual, valorTotal, parcelas);
//		System.out.println("7 vezes : " + retorno7.doubleValue()+ " " + (retorno7.multiply(new BigDecimal(parcelas))));
//		
//		parcelas = 8;
//		BigDecimal retorno8 = calculaValorParcelado(valorPercentual, valorTotal, parcelas);
//		System.out.println("8 vezes : " + retorno8.doubleValue()+ " " + (retorno8.multiply(new BigDecimal(parcelas))));
//
//		parcelas = 9;
//		BigDecimal retorno9 = calculaValorParcelado(valorPercentual, valorTotal, parcelas);
//		System.out.println("9 vezes : " + retorno9.doubleValue()+ " " + (retorno9.multiply(new BigDecimal(parcelas))));
//
//		parcelas = 10;
//		BigDecimal retorno10 = calculaValorParcelado(valorPercentual, valorTotal, parcelas);
//		System.out.println("10 vezes : " + retorno10.doubleValue()+ " " + (retorno10.multiply(new BigDecimal(parcelas))));
//		
//    	BigDecimal valorTotal  = new BigDecimal(1025.05);
//		NumberFormat nf = new DecimalFormat("#####.##");
//		nf.setMaximumFractionDigits(2);
//		nf.setMinimumFractionDigits(2);
//    	
//		System.out.println(Long.parseLong(convertBigDecimalToStringSemSeparador(valorTotal)));
		
    	String input = "Calendários";  
		
//		System.out.println(removeAcentuacao(input));
    }

    public static String convertTimeToString(Time t) {
        String retorno = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            retorno = sdf.format(t.getTime());
        }
        catch (Exception e) {
        }

        return retorno;
    }

    /**
     * 
     * @param criptoString
     * @return
     * @throws IOException
     */
    public static String descriptoBase64(String criptoString) throws Exception {
        String decript = null;
        try {
            decript = new String(new sun.misc.BASE64Decoder().decodeBuffer(criptoString));
        }
        catch (Exception e) {
            throw e;
        }

        return decript;
    }

    public static String encriptBase64(String criptoString)  {
        String encript = null;

        try {
            encript = new String(new sun.misc.BASE64Encoder().encode(criptoString.getBytes()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        return encript;
    }

    public static Byte[] converterDataByte(File anex) {
        byte[] b = new byte[(int) anex.length()];
        
        Byte[] byteObjects = new Byte[b.length];

        try {
            FileInputStream fileInputStream = new FileInputStream(anex);
            fileInputStream.read(b);
            for (int i = 0; i < b.length; i++) {
                System.out.print((char) b[i]);
            }
            
            try{
            	fileInputStream.close();
            }catch(Exception e){
            }
            
            int i=0;    
	         // Associating Byte array values with bytes. (byte[] to Byte[])
	         for(byte bx: b)
	            byteObjects[i++] = bx; 
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return byteObjects;
    }

    public static final String xedit(String txt, int len) {
        StringBuffer s;

        if (txt == null)
            s = new StringBuffer();
        else
            s = new StringBuffer(txt);

        for (int i = s.length(); i < len; ++i)
            s.append(' ');

        return s.toString().substring(0, len);
    }

    public static final String nedit(String txt, int len) {
        StringBuffer s;

        if (txt == null)
            s = new StringBuffer();
        else
            s = new StringBuffer(txt);

        for (int i = s.length(); i < len; ++i)
            s.insert(0, '0');

        return s.toString().substring(s.length() - len, s.length());
    }

    public static final String redit(String txt, int len) {
        StringBuffer s;

        if (txt == null)
            s = new StringBuffer();
        else
            s = new StringBuffer(txt);

        for (int i = s.length(); i < len; ++i)
            s.insert(0, ' ');

        return s.toString().substring(s.length() - len, s.length());
    }

    public static final String redit(String txt, int len, char ch) {
        StringBuffer s;

        if (txt == null)
            s = new StringBuffer();
        else
            s = new StringBuffer(txt);

        for (int i = s.length(); i < len; ++i)
            s.insert(0, ch);

        return s.toString().substring(s.length() - len, s.length());
    }

    /**
     * Retorna um string vazio se p for nulo ou o próprio p.
     */
    public static final String zn(String p) {
        if (p == null)
            return EMPTY_STRING;
        else
            return p;
    }

    /**
     * Retorna nulo se p for nulo ou um string vazio ou retorna o próprio p.
     */
    public static final String nz(String p) {
        if (p == null || p.length() == 0)
            return null;
        else
            return p;
    }

    public static final String nz(Object p) {
        if (p == null)
            return null;
        else
            return nz(p.toString());
    }

    public static boolean verifyCGC(String cgc) {
        if (cgc.length() > 0) {
            char c = cgc.charAt(0);
            int pos = 1;
            for (; pos < cgc.length(); ++pos)
                if (c != cgc.charAt(pos))
                    break;

            if (pos >= cgc.length())
                return false;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cgc.length(); ++i)
            if (cgc.charAt(i) >= '0' && cgc.charAt(i) <= '9')
                sb.append(cgc.charAt(i));

        byte[] n = UtilFunction.nedit(sb.toString(), 15).getBytes(); // Fica um
        // zero
        // a
        // esquerda
        // a
        // mais

        for (int i = 0; i < n.length; ++i)
            n[i] = (byte) (n[i] - '0');

        int DF1 = (5 * n[1]) + (4 * n[2]) + (3 * n[3]) + (2 * n[4]) + (9 * n[5]) + (8 * n[6]) + (7 * n[7]) + (6 * n[8])
            + (5 * n[9]) + (4 * n[10]) + (3 * n[11]) + (2 * n[12]);
        int DF2 = DF1 / 11;
        int DF3 = DF2 * 11;
        int Resto1 = DF1 - DF3;
        int DIG1 = 0;

        if (Resto1 == 0 || Resto1 == 1)
            DIG1 = 0;
        else
            DIG1 = 11 - Resto1;

        int DF4 = (6 * n[1]) + (5 * n[2]) + (4 * n[3]) + (3 * n[4]) + (2 * n[5]) + (9 * n[6]) + (8 * n[7]) + (7 * n[8])
            + (6 * n[9]) + (5 * n[10]) + (4 * n[11]) + (3 * n[12]) + (2 * DIG1);

        int DF5 = DF4 / 11;
        int DF6 = DF5 * 11;

        int Resto2 = DF4 - DF6;
        int DIG2 = 0;

        if (Resto2 == 0 || Resto2 == 1)
            DIG2 = 0;
        else
            DIG2 = 11 - Resto2;

        if (DIG1 != n[13] || DIG2 != n[14])
            return false;
        else
            return true;
    }

    public static boolean verifyCPF(String cpf) // string com 11 posições
    {
        if (cpf == null)
            return false;

        if (cpf.length() > 0) {
            char c = cpf.charAt(0);
            int pos = 1;
            for (; pos < cpf.length(); ++pos)
                if (c != cpf.charAt(pos))
                    break;

            if (pos >= cpf.length())
                return false;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cpf.length(); ++i)
            if (cpf.charAt(i) >= '0' && cpf.charAt(i) <= '9')
                sb.append(cpf.charAt(i));

        cpf = UtilFunction.nedit(sb.toString(), 11);

        int soma;
        int inicio = 2;
        int fim = 10;
        int digito = 0;
        String controle = "";

        for (int j = 1; j <= 2; ++j) {
            soma = 0;
            for (int i = inicio; i <= fim; ++i)
                soma += (cpf.charAt(i - j - 1) - '0') * (fim + 1 + j - i);

            if (j == 2)
                soma += (2 * digito);

            digito = (soma * 10) % 11;
            if (digito == 10)
                digito = 0;

            controle += String.valueOf(digito);
            inicio = 3;
            fim = 11;
        }

        return controle.equals(cpf.substring(9));
    }

     
    
    


    public static String formatarCnpj(String cnpj) {

        if (cnpj == null) {
            return EMPTY_STRING;
        }

        while (cnpj.length() < 14) {
            cnpj = "0" + cnpj;
        }
        return formatar(cnpj, "##.###.###/####-##");
    }

    public static String formatarCpf(String cnpj) {

        if (cnpj == null) {
            return EMPTY_STRING;
        }

        while (cnpj.length() < 14) {
            cnpj = "0" + cnpj;
        }
        return formatar(cnpj, "###.###.###/##");
    }

    public static String formatar(String valor, String mascara) {
        String dado = "";
        // remove caracteres nao numericos

        for (int i = 0; i < valor.length(); i++) {
            char c = valor.charAt(i);
            if (Character.isDigit(c)) {
                dado += c;
            }
        }

        int indMascara = mascara.length();
        int indCampo = dado.length();

        for (; indCampo > 0 && indMascara > 0;) {
            if (mascara.charAt(--indMascara) == '#') {
                indCampo--;
            }
        }

        String saida = "";

        for (; indMascara < mascara.length(); indMascara++) {
            saida += ((mascara.charAt(indMascara) == '#') ? dado.charAt(indCampo++) : mascara.charAt(indMascara));
        }

        return saida;
    }

    /**
     * Verifica se o parametro passado é cpf ou cpnj e formata como tal
     * 
     * @param cpfCnpjDoador
     * @return
     */
    public static String formatarCpfCnpj(String cpfCnpjDoador) {
        try {

            if (cpfCnpjDoador == null) {
                return cpfCnpjDoador;
            }

            if (UtilFunction.verifyCPF(cpfCnpjDoador)) {
                return UtilFunction.formatarCpf(cpfCnpjDoador);
            }

            if (UtilFunction.verifyCGC(cpfCnpjDoador)) {
                return UtilFunction.formatarCnpj(cpfCnpjDoador);
            }

        }
        catch (Exception e) {
        }

        return cpfCnpjDoador;
    }

    /**
     * 
     * @param criptoString
     * @return
     * @throws IOException
     */
    public static String getDescriptoBase64(String criptoString) throws IOException {
        String decript = new String(new sun.misc.BASE64Decoder().decodeBuffer(criptoString));
        return decript;
    }

    // public static void main(String[] args) throws Exception {
    // BASE64Encoder enc = new BASE64Encoder();
    // System.out.println(enc.encodeBuffer("589002".getBytes()));
    //		
    // String decript = new String(new
    // BASE64Decoder().decodeBuffer("MDAwNjAzODkx"));
    // System.out.println(decript);
    // }

    public static Integer toInteger(java.math.BigDecimal objeto) {
        if (objeto == null) {
            return 0;
        }
        else {
            return objeto.intValue();
        }
    }

    public static Double toDouble(BigDecimal bd) {
        return bd == null ? 0 : bd.doubleValue();
    }

    public static String valueOf(Object objeto) {
        if (objeto == null) {
            return EMPTY_STRING;
        }
        else {
            return String.valueOf(objeto);
        }
    }

    public static boolean isNotBlankOrNull(Object o) {
        return !isBlankOrNull(o);
    }
    
    public static boolean isNotBlankOrNullContemStringNull(Object o) {
    	boolean ehBrancoOuNullo = isBlankOrNull(o);
    	
    	if(!ehBrancoOuNullo)
    	   return !("null".equalsIgnoreCase(((String) o)));
    	
        return !ehBrancoOuNullo;
    }

    public static boolean isBlankOrNull(Object o) {
        if (o == null) {
            return true;
        }

        if (EMPTY_STRING.equals(o.toString())) {
            return true;
        }

        return false;
    }

    public static boolean isBlankZeroOrNull(Object o) {
        if (o == null) {
            return true;
        }

        if (o instanceof Number) {

            if ((((Number) o)).intValue() == 0) {
                return true;
            }

        }

        if (EMPTY_STRING.equals(o.toString())) {
            return true;
        }

        return false;
    }

    public static String getDataFormatada() {
        String retorno = EMPTY_STRING;
        Date d = new Date();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yy"); // 17:00:00
            // 24/08/2010
            retorno = sdf.format(d);
        }
        catch (Exception e) {
        }

        return retorno;
    }

    public static boolean notIsEmpty(Object o) {
        return !isEmpty(o);
    }

    @SuppressWarnings("unchecked")
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }

        if (o instanceof String && ((String) o).trim().equals(EMPTY_STRING)) {
            return true;
        }

        if (o instanceof Collection && ((Collection) o).isEmpty()) {
            return true;
        }

        if (o instanceof Map && ((Map) o).isEmpty()) {
            return true;
        }

        return false;
    }

    public static BigDecimal formatarTelefone(String telefone) {
        if (isNotBlankOrNull(telefone)) {
            if (!telefone.equals("null")) {
                telefone = telefone.replaceAll("[ ()-]", "");
                return new BigDecimal(telefone);
            }
        }
        return new BigDecimal(0);
    }

    public static String formatarCNPJ(String cnpj) {
        if (isNotBlankOrNull(cnpj)) {
            if (!cnpj.equals("null")) {
                cnpj = cnpj.replaceAll("[ ./-]", "");
                return cnpj;
            }
        }
        return "";
    }

    public static String maskFormaterTelefone(Long nrTelefone) {
        Formatter m = null;
        String retorno = nrTelefone != null ? nrTelefone.toString() : null;

        try {
            m = new Formatter();
            m.format("(##) ####-####", nrTelefone);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }

    public static Date hoje() {
    	Calendar c = Calendar.getInstance(new Locale("pt", "BR"));
        return c.getTime();
    }

    public static Timestamp hojeDataHoraTimesTamp() {
        return new Timestamp(new Date().getTime());
    }

    public static Date hojeDataHora() {
        return hojeDataHoraTimesTamp();
    }

    /*
     * recebe a data no formato 25-Jan-2000
     */
    public static Date convertDate(String data) {
        Date retorno = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

        try {
            retorno = sdf.parse(data);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }
    
    public static Date stringToDate(String data, String formato) {
        Date retorno = null;
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        sdf.setLenient(false);

        try {
            retorno = sdf.parse(data);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }    

    

    private static String toLongString(Double d) {
        return d != null ? String.valueOf(d.longValue()) : "";
    }

     

    public static boolean verificaAmbiente(AmbienteEnum ambiente) {

        String runningMode = System.getProperty("runningMode");
        if (notEmpty(runningMode) && ambiente.getNmAmbiente().equals(runningMode)) {
            return true;
        }

        return false;
    }
    
    public static boolean verificaAmbienteProd() {
    	AmbienteEnum ambiente = AmbienteEnum.PROD;
        String runningMode = System.getProperty("runningMode");
        if (notEmpty(runningMode) && ambiente.getNmAmbiente().equals(runningMode)) {
            return true;
        }

        return false;
    }

    public static boolean notEmpty(Object o) {
        return !isEmpty(o);
    }

    /**
     * @author asimas
     */
    public enum AmbienteEnum implements Serializable {

        DEV("dev"), PI("pi"), PROD("prod"), HMG("hml");

        private AmbienteEnum(String nmAmbiente) {
            this.nmAmbiente = nmAmbiente;
        }

        private String nmAmbiente;

        public String getNmAmbiente() {
            return nmAmbiente;
        }

        public void setNmAmbiente(String nmAmbiente) {
            this.nmAmbiente = nmAmbiente;
        }

    }

    public static BigDecimal stringToBigDecimal(String telefone) {
        BigDecimal retorno = new BigDecimal(0);
        if (telefone == null) {
            return new BigDecimal(0);
        }

        try {
            retorno = new BigDecimal(telefone);
        }
        catch (Exception e) {
            telefone = telefone.replace(',', '.');
            retorno = new BigDecimal(telefone);
        }

        return retorno;
    }

    public static String getString(String string) {
        if (string == null) {
            return "";
        }
        else {
            return string;
        }
    }

    public static String getString(Integer i) {
        if (i == null) {
            return "";
        }
        else {
            return String.valueOf(i);
        }
    }

    public static Long desformataTelefone(String telefoneFormatado) {
        Long retorno = new Long(0);

        if (telefoneFormatado == null) {
            return null;
        }

        telefoneFormatado = telefoneFormatado.replace("(", "");
        telefoneFormatado = telefoneFormatado.replace(")", "");
        telefoneFormatado = telefoneFormatado.replace(" ", "");
        telefoneFormatado = telefoneFormatado.replace("-", "");
        telefoneFormatado = telefoneFormatado.replace(".", "");
        telefoneFormatado = telefoneFormatado.trim();

        try {
            retorno = Long.parseLong(telefoneFormatado);
        }
        catch (Exception e) {
            return null;
        }

        return retorno;
    }

    public static boolean ehIgualLong(Long codigoPerfil, Long codigoPerfil2) {

        return (codigoPerfil.intValue() == codigoPerfil2.intValue());

    }

    public static Integer parseIdPaisEmpresa(String s, int posicao) {
        Integer[] retorno = new Integer[2];
        retorno[0] = 0;
        retorno[1] = 0;

        if (s == null) {
            return retorno[posicao];
        }

        try {
            retorno[0] = Integer.parseInt(s.substring(0, s.indexOf("-")));
            retorno[1] = Integer.parseInt(s.substring(s.indexOf("-") + 1, s.length()));
        }
        catch (Exception e) {

        }

        return retorno[posicao];
    }

    public static boolean ehIgualLong(Integer cdRecu, Integer cdRecu2) {
        return (cdRecu.intValue() == cdRecu2.intValue());
    }

    public static String generateNewPassword() {
        Random r = new Random();
        String s = String.valueOf(r.nextLong());

        while (s.length() < 7) {
            s = s + "a";
        }

        s = s.replace("-", "Z");

        return s.substring(0, 7);
    }

    public static String formatarCodigoPostalBR(String codigoPostal) {
        String codigoPostalFormatado = "";
        if (codigoPostal.length() == 8) {
            codigoPostalFormatado = codigoPostal.substring(0, 5) + "-" + codigoPostal.substring(5, 8);
        }
        else {
            return codigoPostal;
        }
        return codigoPostalFormatado;
    }

    public static String formatarTelefoneBR(String telefone) {


        if (telefone == null) {
            return "";
        }

        if ("".equals(telefone)) {
            return "";
        }

        String telFormatado = "";
        if (telefone.length() == 10) {
            telFormatado = "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 6) + "-"
                + telefone.substring(6, 10);
        }
        else {
            return telefone;
        }
        return telFormatado;
    }

    public static String formatarTelefoneES(String telefone) {

        if (telefone == null) {
            return "";
        }

        if ("".equals(telefone)) {
            return "";
        }

        if (telefone.length() == 9) {
            return new StringBuilder(telefone.substring(0, 2)).append(" ").append(telefone.substring(2, 5)).append(" ")
                .append(telefone.substring(5, 9)).toString();
        }
        return telefone;
    }

    public static String formatarTelaCNPJ(String cnpj) {
        String cnpjFormat = "";
        if (cnpj.length() == 14) {
            cnpjFormat = cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/"
                + cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14);

            return cnpjFormat;
        }
        else {
            return cnpj;
        }
    }

    /**
     * Formata o numero, Objeto pode ser Double, Long, Integer,BigDecimal,etc.
     * 
     * @param o
     * @return
     */
    public static String formatNumber(Object o) {
        if (o == null)
            return "";
        NumberFormat form = NumberFormat.getInstance(new Locale("en", "US"));
        form.setMinimumFractionDigits(2);
        form.setMaximumFractionDigits(2);
        return form.format(o);
    }

    public static BigDecimal parseNumber(String s) {
        if (isBlankZeroOrNull(s))
            return BigDecimal.ZERO;
        NumberFormat form = NumberFormat.getInstance(new Locale("es", "ES"));
        Number n;
        try {
            n = form.parse(s);
            return new BigDecimal(n.toString());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }


    public static boolean isNotEmpty(List its) {
        return (its != null && !its.isEmpty());
    }

    public static Integer converterBigDecimalToInteger(Object object) {

        if (object == null) {
            return 0;
        }

        if (object instanceof java.math.BigDecimal) {
            return ((BigDecimal) object).intValue();
        }

        if (object instanceof Integer) {
            return ((Integer) object).intValue();
        }
        
        if (object instanceof Long) {
            return ((Long) object).intValue();
        }

        return 0;
    }

    public static Date convertDataHora(String dataFaseCronograma, String horaFaseCronograma) {
        if (isEmpty(dataFaseCronograma) || isEmpty(horaFaseCronograma)) {
            return null;
        }

        Date retorno = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            retorno = sdf.parse(dataFaseCronograma + " " + horaFaseCronograma);
        }
        catch (Exception e) {
            retorno = null;
        }

        return retorno;
    }

    /**
     * Retorna a data com a hora setada.
     * 
     * @param data
     * @param hora
     * @return
     */
    public static Date colocarHorarioData(Date data, String hora) {
        if (isEmpty(hora)) {
            return null;
        }

        try {
            SimpleDateFormat formatoDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            String strData = formatoData.format(data);

            return formatoDataHora.parse(strData + " " + hora);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static int compararDatas(Date data1, Date data2) {
        if (data1 == null)
            return -1;
        if (data2 == null)
            return 1;
        return data1.compareTo(data2);
    }

    public static String getString(long nrSequCnrr) {
        if (nrSequCnrr == 0) {
            return "";
        }


        return String.valueOf(nrSequCnrr);
    }

    public static String getHoraByDate(Date data) {
        String retorno = null;

        if (data == null) {
            return retorno;
        }

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            retorno = sdf.format(data);

        }
        catch (Exception e) {
        }

        return retorno;
    }

    public static String dateToString(Date data) {
        String retorno = null;

        if (data == null) {
            return retorno;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            retorno = sdf.format(data);
        }
        catch (Exception e) {
            retorno = null;
        }


        return retorno;
    }
    
    public static String dateToString(Date data, String mascara) {
        String retorno = null;

        if (data == null) {
            return retorno;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(mascara);
            retorno = sdf.format(data);
        }
        catch (Exception e) {
            retorno = null;
        }


        return retorno;
    }

    public static boolean validarFormatoDataHora(String data, String formatoData) {
        if (data == null)
            return false;
        try {
            new SimpleDateFormat(formatoData).parse(data);
            return true;
        }
        catch (ParseException e) {
            return false;
        }
    }

    public static String getNomeArquivo(String caminhCompleto) {
        String nomeDoArquivo = null;

        if ("".equals(caminhCompleto) || caminhCompleto == null) {
            return null;
        }
        nomeDoArquivo = caminhCompleto.substring(caminhCompleto.lastIndexOf("\\") + 1, caminhCompleto.length());


        return nomeDoArquivo;
    }

    public static String getTipoArquivo(String nmEndeAnex) {

        if ("".equals(nmEndeAnex) || nmEndeAnex == null) {
            return "";
        }

        String retorno = null;
        retorno = nmEndeAnex.substring(nmEndeAnex.lastIndexOf(".") + 1, nmEndeAnex.length());

        if ("xls".equalsIgnoreCase(retorno)) {
            return "application/xls";
        }

        if ("pdf".equalsIgnoreCase(retorno)) {
            return "application/pdf";
        }

        if ("ppt".equalsIgnoreCase(retorno)) {
            return "application/ppt";
        }

        if ("gif".equalsIgnoreCase(retorno)) {
            return "image/gif";
        }

        if ("jpeg".equalsIgnoreCase(retorno)) {
            return "image/jpeg";
        }

        if ("jpg".equalsIgnoreCase(retorno)) {
            return "image/jpg";
        }

        return retorno;
    }

    public static boolean verificarDataMenorDiaCorrente(Date data, TimeZone timeZone) {
        Date hoje = hojeZeroHoras(timeZone);

        return data.before(hoje);
    }

    public static Date hojeZeroHoras(TimeZone timeZone) {
        Calendar c = Calendar.getInstance(timeZone);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static InputStream obterArquivoDaRede(String caminho, String nomeArquivo) {
        InputStream in = null;

        try {
            File retorno = new File(caminho + nomeArquivo);
            in = new FileInputStream(retorno);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return in;
    }

    public static byte[] converterDataByteToFile(File anex) {
        byte[] b = new byte[(int) anex.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(anex);
            fileInputStream.read(b);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public static String getUrlExternaConcorrencia() {
        String retorno = null;
        // http://180.128.113.79:8080/gcf/externo/compras/avaliacao_tecnica.iface?NR_CNRR=162
        if (UtilFunction.verificaAmbiente(AmbienteEnum.DEV)) {
            retorno = "http://portaldotnetdes.sb/gcf/externo/compras/avaliacao_tecnica.iface?NR_CNRR=";
        }

        if (UtilFunction.verificaAmbiente(AmbienteEnum.HMG)) {
            retorno = "http://portaljavapi.sb/gcf/externo/compras/avaliacao_tecnica.iface?NR_CNRR=";
        }

        if (UtilFunction.verificaAmbiente(AmbienteEnum.PI)) {
            retorno = "http://portaljavapi.sb/gcf/externo/compras/avaliacao_tecnica.iface?NR_CNRR=";
        }

        if (UtilFunction.verificaAmbiente(AmbienteEnum.PROD)) {
            retorno = "http://portaljava.bs.br.bsch/gcf/externo/compras/avaliacao_tecnica.iface?NR_CNRR=";
        }

        return retorno;
    }

    public static String substring(String s, int max) {
        if (s == null)
            return null;
        if (s.length() > max)
            s = s.substring(0, max);
        return s;
    }

    public static Date parseDate(String s, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(s);
    }

    /**
     * quantidade de zeros é o valor - a quantidade informada.
     * 
     * @param valor
     * @param quantidade
     * @return Valor
     */
    public static String preencherZeroEsquerda(String valor, Integer quantidade) {
        String s = valor;
        if (s.length() < quantidade) {
            int zeros = quantidade - s.length();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < zeros; i++) {
                buffer.append("0");
            }
            buffer.append(s);
            s = buffer.toString();
        }
        return s;
    }


    public static int calcularAno(int soma) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, soma);

        return c.get(Calendar.YEAR);
    }
    
    public static Date calcularDia(int soma) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, soma);
        return c.getTime();
    }
    
    public static Date calcularHora(int soma) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, soma);
        return c.getTime();
    }
    
    public static Date calcularDia(Date dataInicial, int soma) {
        Calendar c = Calendar.getInstance();
        c.setTime(dataInicial);
        c.add(Calendar.DATE, soma);
        return c.getTime();
    }
    

    public static BigDecimal dataBaseToBigDecimal(Object object) {
        Integer obj = null;

        try {
            obj = (Integer) object;

            if (obj == null) {
                obj = 0;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            obj = 0;
        }

        return new BigDecimal(obj);
    }


    public static Date createDate(String data) {
        Date dt = null;
        try {
            SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            dt = dtf.parse(data);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }


    public static String convertBigDecimalToStringFormatado(BigDecimal d) {

        if (d == null) {
            return "R$ 0,00 ";
        }

        String conv = null;
        NumberFormat format = java.text.DecimalFormat.getInstance();
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(2);

        try {
            conv = "R$ " + format.format(d);
            return conv;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static String convertBigDecimalToString(BigDecimal d) {

        if (d == null) {
            return "";
        }

        String conv = null;
        NumberFormat format = java.text.DecimalFormat.getInstance(new Locale("pt", "BR"));
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(2);

        try {
            conv = format.format(d);
            return conv;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    

    public static Date getHojeSomaMes(int qtdMeses) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, qtdMeses);
        return cal.getTime();
    }

    public static String getHojeSomaMesDDMMYYYY(int qtdMeses) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, qtdMeses);
        return getDataDDMMYYYY(cal.getTime());
    }

    
    public static String getDataDDMMYYYY(Date data) {
        String retorno = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            retorno = sdf.format(data);
        }
        catch (Exception e) {
        }

        return retorno;
    }

    public static BigDecimal calculaPercentual(BigDecimal valorUnitario, BigDecimal descontoPercentual) {
        descontoPercentual = descontoPercentual.divide(new BigDecimal(100));
        valorUnitario      = valorUnitario.subtract(descontoPercentual.multiply(valorUnitario));
        return valorUnitario;
    }

    public static String getMMYYYY(Date time) {
        String retorno = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
            retorno = sdf.format(time);
        }
        catch (Exception e) {
        }

        return retorno;
    }

    public static Time createTime(String horaInicio, Locale locale) {
        Calendar cal = Calendar.getInstance(locale);
        
        String hora   = horaInicio.substring(0, horaInicio.indexOf(":"));
        String minuto = horaInicio.substring(horaInicio.indexOf(":")+1, horaInicio.length());
        
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora));
        cal.set(Calendar.MINUTE, Integer.parseInt(minuto));
        cal.set(Calendar.SECOND, 00);
        Time horan = new Time(cal.getTime().getTime());
        return horan;
    }

    public static String getDD(Date time) {
        String retorno = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd");
            retorno = sdf.format(time);
        }
        catch (Exception e) {
        }

        return retorno;
    }
    
    public static String getMM(Date time) {
        String retorno = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM");
            retorno = sdf.format(time);
        }
        catch (Exception e) {
        }

        return retorno;
    }

    public static List<String> listarDezAnos() {
        List<String> retornos = new ArrayList<String>();

        try{
            
            SimpleDateFormat sdf = new SimpleDateFormat("yy");
            String anoAtual     = sdf.format(UtilFunction.hoje());
            retornos.add(anoAtual);
            
            Integer anoAtuali = Integer.parseInt(anoAtual);
                
            for(int i = 0 ; i < 10; i++){
                anoAtuali = anoAtuali+1;
                retornos.add(UtilFunction.preencherZeroEsquerda(""+anoAtuali,2));
            }
        
        }catch(Exception e){
            
        }
        
        return retornos;
    }

    public static List<String> listarMesesDoAnoInteiros() {

        List<String> retorno = new ArrayList<String>();
        
        for(int i = 1 ; i <= 12; i++){
            retorno.add(UtilFunction.preencherZeroEsquerda(""+i,2));
        }
        
        return retorno;
    }
    
    public static Date truncDate(Date dataHora){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(sdf.format(dataHora));
        }
        catch (ParseException e) {
                        e.printStackTrace();
        }
        
        return hoje();
    }
    
	public static File converteByteArrayToFile(byte[] bFile, String diretorio) {
		try{
			
			String extensao = diretorio.substring(diretorio.length()-3, diretorio.length());
			File f = new File(diretorio);
			
				BufferedImage img = ImageIO.read(new ByteArrayInputStream(bFile));
				ImageIO.write(img, extensao, f);
			
			
			
//			FileOutputStream fout = new FileOutputStream(f);
//			BufferedOutputStream fileOuputStream = new BufferedOutputStream(fout); 
//			fileOuputStream.write(bFile);
//			fileOuputStream.close();
			return f;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	public static BigDecimal convertStringValorCifraoToBigDecimal(
			String valorFrete) {
		
		if(isBlankOrNull(valorFrete)){
			return BigDecimal.ZERO;
		}
		
		valorFrete         = valorFrete.replace("R$", "");
		valorFrete         = valorFrete.trim();
		
		BigDecimal retorno = null;
		
		try{
			valorFrete = valorFrete.replace("R$", "");
			
			 NumberFormat format = java.text.DecimalFormat.getInstance(new Locale("pt", "BR"));
		     format.setMaximumFractionDigits(2);
		     format.setMinimumFractionDigits(2);
			
			Number n = format.parse(valorFrete);
			
			retorno = new BigDecimal(n.doubleValue());
			
		}catch(Exception e){
			retorno = BigDecimal.ZERO;
		}
		
		return retorno;
	}
	
	
	public static long converteBigDecimalToLong(BigDecimal valorTotalDoPedido){
		
		if(valorTotalDoPedido == null){
			return 0;
		}
		
		double d = valorTotalDoPedido.doubleValue();
		
		String s = String.valueOf(d);
		
		s = s.replace(".", "");
		s = s.replace(",", "");
		
		Long l = new Long(s);
		
		return l;
		
	}
	

	/**
	 * 
	 * @param valorPercentualAdicionar
	 * @param valorTotal
	 * @param parcelas
	 * @return valor da parcela acrescida de juros sob a fórmula 
	 * 
	 * M = C x (1 + i/100)^t
	 * 
	 * Onde
	 * 
	 *  m = Montante	24,98496	
		c = Capital	23,1	
		i = Juros	4	
		t = Tempo	2	Meses
		Vlr Juros	1,88496	
	 * 
	 */
	public static BigDecimal calculaValorParcelado(Double valorPercentualAdicionar,
			BigDecimal valorTotal, int parcelas) {
		
		BigDecimal valorParcela = new BigDecimal(0);
//		valorParcela            = valorTotal.divide(new BigDecimal(parcelas), RoundingMode.HALF_UP);
//		BigDecimal percentual   = new BigDecimal(valorPercentualAdicionar);
//		valorParcela            = valorParcela.add(valorParcela.multiply(percentual.divide(new BigDecimal(100))));
		
		BigDecimal montante = BigDecimal.ZERO;
		BigDecimal capital  = valorTotal;
		BigDecimal juros    = new BigDecimal(valorPercentualAdicionar);
		int tempo           = parcelas;
		BigDecimal hum      = BigDecimal.ONE;
		BigDecimal cem      = new BigDecimal(100);
		
		montante = capital.multiply(hum.add(juros.divide(cem)).pow(tempo));
		
		valorParcela = montante.divide(new BigDecimal(tempo), RoundingMode.HALF_UP);
		
		return valorParcela;
	}
	
	public static boolean isNumeric(String valor){
		
		boolean retorno = false;
		
		try{
			Integer.parseInt(valor);
			retorno = true;
		}catch(Exception e){
			retorno =false;
		}
		
		return retorno;
		
	}
	

	public static InputStream buscarStream(String nome) {
		return UtilFunction.class.getResourceAsStream(nome);
	}
	
 

}
