package br.com.intuitiv.templatespring.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DataUtil {

  public static final int FIRST_DAY_OF_MONTH = 1;
  public static final String MASCARA_DATA_HORA = "dd/MM/yyyy HH:mm:ss";
  public static final String MASCARA_DATA = "dd/MM/yyyy";
  private static final Locale LOCALE_BRASIL = new Locale("pt", "BR");

  public static Date asDate(final LocalDate localDate) {

    if (localDate == null) {
      return null;
    }

    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }

  public static Date asDate(final LocalDateTime localDateTime) {

    if (localDateTime == null) {
      return null;
    }
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  public static LocalDate asLocalDate(final Date data) {

    if (data == null) {
      return null;
    }
    return Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public static LocalDateTime asLocalDateTime(final Date data) {

    if (data == null) {
      return null;
    }
    return Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  public static Date completarPrimeiraHoraDoDia(final Date data) {

    if (data == null) {
      return null;
    }
    return DataUtil.asDate(DataUtil.completarPrimeiraHoraDoDia(DataUtil.asLocalDateTime(data)));
  }

  public static LocalDateTime completarPrimeiraHoraDoDia(final LocalDateTime data) {

    if (data == null) {
      return null;
    }
    return LocalDateTime.of(
        data.getYear(), data.getMonth().getValue(), data.getDayOfMonth(), 0, 0, 0, 0);
  }

  public static LocalDateTime completarDataComHorarioAtual(final LocalDateTime data) {

    if (data == null) {
      return null;
    }

    final Calendar horarioAtual = Calendar.getInstance();
    horarioAtual.setTime(new Date());

    final int hour = horarioAtual.get(Calendar.HOUR_OF_DAY);
    final int minute = horarioAtual.get(Calendar.MINUTE);
    final int second = horarioAtual.get(Calendar.SECOND);
    final int millisecond = horarioAtual.get(Calendar.MILLISECOND);

    final Calendar cal = Calendar.getInstance();
    cal.setTime(DataUtil.asDate(data));
    cal.set(Calendar.HOUR_OF_DAY, hour);
    cal.set(Calendar.MINUTE, minute);
    cal.set(Calendar.SECOND, second);
    cal.set(Calendar.MILLISECOND, millisecond);

    return DataUtil.asLocalDateTime(cal.getTime());
  }

  public static Date completarUltimaHoraDoDia(final Date data) {

    if (data == null) {
      return null;
    }
    return DataUtil.asDate(DataUtil.completarUltimaHoraDoDia(DataUtil.asLocalDateTime(data)));
  }

  public static LocalDateTime completarUltimaHoraDoDia(final LocalDateTime data) {

    if (data == null) {
      return null;
    }
    return LocalDateTime.of(
        data.getYear(), data.getMonth().getValue(), data.getDayOfMonth(), 23, 59, 59, 999999999);
  }

  public static Date completarUltimoDiaAno(final Date data) {

    return DataUtil.asDate(
        LocalDateTime.of(
            DataUtil.getAnoData(data), Month.DECEMBER, Month.DECEMBER.length(false), 23, 59, 59));
  }

  public static Long diferencaEmAnos(final Date dataInicio, final Date dataFim) {

    if ((dataInicio == null) || (dataFim == null)) {
      return null;
    }
    return ChronoUnit.YEARS.between(
        DataUtil.asLocalDate(dataInicio), DataUtil.asLocalDate(dataFim));
  }

  public static Long diferencaEmAnos(final LocalDate dataInicio, final LocalDate dataFim) {

    if ((dataInicio == null) || (dataFim == null)) {
      return null;
    }
    return ChronoUnit.YEARS.between(dataInicio, dataFim);
  }

  public static Long diferencaEmDias(final Date dataInicio, final Date dataFim) {

    if ((dataInicio == null) || (dataFim == null)) {
      return null;
    }
    return ChronoUnit.DAYS.between(DataUtil.asLocalDate(dataInicio), DataUtil.asLocalDate(dataFim));
  }

  public static Long diferencaEmDias(final LocalDate dataInicio, final LocalDate dataFim) {

    if ((dataInicio == null) || (dataFim == null)) {
      return null;
    }
    return ChronoUnit.DAYS.between(dataInicio, dataFim);
  }

  public static Long diferencaEmDiasSemHoras(final Date dataInicio, final Date dataFim) {

    if ((dataInicio == null) || (dataFim == null)) {
      return null;
    }
    return ChronoUnit.DAYS.between(
        DataUtil.asLocalDate(DataUtil.completarPrimeiraHoraDoDia(dataInicio)),
        DataUtil.asLocalDate(DataUtil.completarPrimeiraHoraDoDia(dataFim)));
  }

  public static Long diferencaEmHoras(final Date dataInicio, final Date dataFim) {

    if ((dataInicio == null) || (dataFim == null)) {
      return null;
    }
    return DataUtil.diferencaEmHoras(
        DataUtil.asLocalDateTime(dataInicio), DataUtil.asLocalDateTime(dataFim));
  }

  public static Long diferencaEmHoras(final LocalDateTime dataInicio, final LocalDateTime dataFim) {

    if ((dataInicio == null) || (dataFim == null)) {
      return null;
    }
    return ChronoUnit.HOURS.between(dataInicio, dataFim);
  }

  public static Long diferencaEmMeses(final Date dataInicio, final Date dataFim) {

    if ((dataInicio == null) || (dataFim == null)) {
      return null;
    }
    return ChronoUnit.MONTHS.between(
        DataUtil.asLocalDate(dataInicio).withDayOfMonth(1),
        DataUtil.asLocalDate(dataFim).withDayOfMonth(1));
  }

  public static Long diferencaEmMeses(final LocalDate dataInicio, final LocalDate dataFim) {

    if ((dataInicio == null) || (dataFim == null)) {
      return null;
    }
    return ChronoUnit.MONTHS.between(dataInicio.withDayOfMonth(1), dataFim.withDayOfMonth(1));
  }

  public static Long diferencaEmMinutos(final Date dataInicio, final Date dataFim) {

    if ((dataInicio == null) || (dataFim == null)) {
      return null;
    }
    return DataUtil.diferencaEmMinutos(
        DataUtil.asLocalDateTime(dataInicio), DataUtil.asLocalDateTime(dataFim));
  }

  public static Long diferencaEmMinutos(
      final LocalDateTime dataInicio, final LocalDateTime dataFim) {

    if ((dataInicio == null) || (dataFim == null)) {
      return null;
    }
    return ChronoUnit.MINUTES.between(dataInicio, dataFim);
  }

  public static String formataData(final Date data) {

    if (data == null) {
      return null;
    }

    return DataUtil.formataData(data, DataUtil.MASCARA_DATA);
  }

  public static String formataData(final Date data, final String pattern) {

    if (data == null) {
      return null;
    }

    final SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    return formatter.format(data);
  }

  public static String formataDataCompleta(final Date data) {

    if (data == null) {
      return null;
    }
    final DateFormat dateFormat =
        DateFormat.getDateInstance(DateFormat.FULL, DataUtil.LOCALE_BRASIL);

    return dateFormat.format(data);
  }

  public static String formataDataHora(final Date data) {

    if (data == null) {
      return null;
    }

    return DataUtil.formataData(data, DataUtil.MASCARA_DATA_HORA);
  }

  public static String formataDataddMMyyyy(final Date data) {

    if (data == null) {
      return null;
    }
    return DataUtil.formataData(data, DataUtil.MASCARA_DATA);
  }

  public static String formataDataHoraMinuto(final Date data) {

    if (data == null) {
      return null;
    }

    return DataUtil.formataData(data, "dd/MM/yyyy HH:mm");
  }

  public static String formataDataHorayyyyMMdd(final Date data) {

    if (data == null) {
      return null;
    }
    return DataUtil.formataData(data, "yyyy/MM/dd");
  }

  public static String formataDataHorayyyyMMddHHmmss(final Date data) {

    if (data == null) {
      return null;
    }
    return DataUtil.formataData(data, "yyyyMMddHHmmss");
  }

  public static String formataDataHorayyyyMMddHHmmssComBarra(final Date data) {

    if (data == null) {
      return null;
    }
    return DataUtil.formataData(data, "yyyy/MM/dd HH:mm:ss");
  }

  public static String formataDataMesAno(final Date data) {

    if (data == null) {
      return null;
    }
    return DataUtil.formataData(data, "MM/yyyy");
  }

  public static String formataDatayyyyMMdd(final Date data) {

    if (data == null) {
      return null;
    }
    return DataUtil.formataData(data, "yyyy-MM-dd");
  }

  public static String formataHora(final Date data) {

    if (data == null) {
      return null;
    }
    return DataUtil.formataData(data, "HH:mm:ss");
  }

  public static Integer getAnoAtual() {

    return LocalDate.now().getYear();
  }

  public static Integer getAnoData(final Date data) {

    if (data == null) {
      return null;
    }
    return DataUtil.asLocalDate(data).getYear();
  }

  public static Integer getAnoData(final LocalDate data) {

    if (data == null) {
      return null;
    }
    return data.getYear();
  }

  public static String getDataHoraMinutoSegundoAtualAsString() {

    return DataUtil.formataData(new Date(), "dd_MM_yyyy_HH_mm_ss_SSS");
  }

  public static Integer getDiaData(final Date data) {

    if (data == null) {
      return null;
    }
    return DataUtil.getDiaData(DataUtil.asLocalDate(data));
  }

  public static Integer getDiaData(final LocalDate data) {

    if (data == null) {
      return null;
    }
    return data.getDayOfMonth();
  }

  public static Integer getDiaHora(final Date data) {

    if (data == null) {
      return null;
    }

    final Calendar calendar = Calendar.getInstance();
    calendar.setTime(data);

    return calendar.get(Calendar.HOUR_OF_DAY);
  }

  public static Integer getDiaHora(final LocalDateTime data) {

    if (data == null) {
      return null;
    }

    return data.getHour();
  }

  public static Integer getDiaMinuto(final Date data) {

    if (data == null) {
      return null;
    }

    final Calendar calendar = Calendar.getInstance();
    calendar.setTime(data);

    return calendar.get(Calendar.MINUTE);
  }

  public static Integer getDiaMinuto(final LocalDateTime data) {

    if (data == null) {
      return null;
    }

    return data.getMinute();
  }

  public static Integer getDiaSegundo(final Date data) {

    if (data == null) {
      return null;
    }

    final Calendar calendar = Calendar.getInstance();
    calendar.setTime(data);

    return calendar.get(Calendar.SECOND);
  }

  public static Integer getDiaSegundo(final LocalDateTime data) {

    if (data == null) {
      return null;
    }

    return data.getSecond();
  }

  public static Integer getIdade(final Date dataNascimento) {

    if (dataNascimento == null) {
      return null;
    }
    return DataUtil.getIdade(DataUtil.asLocalDate(dataNascimento));
  }

  public static Integer getIdade(final LocalDate dataNascimento) {

    if (dataNascimento == null) {
      return null;
    }
    final Period period = Period.between(dataNascimento, LocalDate.now());

    return period.getYears();
  }

  public static Integer getPeridoEmAnos(final LocalDate dataInicio, final LocalDate dataFinal) {

    if (dataInicio == null || dataFinal == null) {

      return null;
    }

    return Period.between(dataInicio, dataFinal).getYears();
  }

  public static String getPeridoPorExtenso(final LocalDate dataInicio, final LocalDate dataFinal) {

    if (dataInicio == null || dataFinal == null) {

      return null;
    }

    final Period period = Period.between(dataInicio, dataFinal);

    final int anos = period.getYears();
    final int meses = period.getMonths();
    int days = period.getDays();

    final StringBuilder sb = new StringBuilder();

    if (anos != 0) {
      sb.append(anos);
      sb.append(anos > 1 ? " anos" : " ano");
    }

    if (meses != 0) {

      if (anos != 0) {
        sb.append(" e ");
      }

      sb.append(meses);
      sb.append(meses > 1 ? " meses" : " mês");
    }

    if (anos == 0 && meses == 0) {

      if (days == 0) {
        days = 1;
      }

      sb.append(days);
      sb.append(days > 1 ? " dias" : " dia");
    }

    return sb.toString();
  }

  public static Integer getMesAtual() {

    return LocalDate.now().getMonthValue();
  }

  public static Integer getMesData(final Date data) {

    if (data == null) {
      return null;
    }
    return DataUtil.asLocalDate(data).getMonthValue();
  }

  public static Integer getMesData(final LocalDate data) {

    if (data == null) {
      return null;
    }
    return data.getMonthValue();
  }

  public static String getMesFormatado(final Integer mesAtual) {

    if (mesAtual == null) {
      return "";
    }

    final Month month = Month.of(mesAtual);

    return month.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
  }

  public static String getPeriodo(final Date dataOperacao) {

    if (dataOperacao == null) {
      return "";
    }

    final double diferencaMinutos = DataUtil.diferencaEmMinutos(dataOperacao, new Date());

    if (diferencaMinutos <= 1d) {
      return "Agora";
    }

    if ((diferencaMinutos > 1d) && (diferencaMinutos < 59d)) {
      return "À " + ((int) diferencaMinutos) + "m";
    }

    final double diferencaHoras = DataUtil.diferencaEmHoras(dataOperacao, new Date());

    if ((diferencaHoras >= 1d) && (diferencaHoras <= 24d)) {
      return "À " + ((int) diferencaHoras) + "h";
    }

    final double diferencaEmDias = DataUtil.diferencaEmDias(dataOperacao, new Date());

    return "À " + ((int) diferencaEmDias) + "d";
  }

  public static Date getPrimeiroDiaDoMes(final Date data) {

    if (data == null) {
      return null;
    }

    final LocalDate localDate = DataUtil.asLocalDate(data);

    return DataUtil.getPrimeiroDiaDoMes(localDate.getYear(), localDate.getMonthValue());
  }

  public static String getDataSistemaOperacional() {

    String s = "";

    try {

      final Process p = Runtime.getRuntime().exec("date");

      final BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

      while ((s = stdInput.readLine()) != null) {
        return s;
      }

    } catch (final IOException e) {
      e.printStackTrace();
    }
    return s;
  }

  public static Date getPrimeiroDiaDoMes(final Integer ano, final Integer mes) {

    return DataUtil.montaData(ano, mes, DataUtil.FIRST_DAY_OF_MONTH);
  }

  public static Date getProximoDiaUtilOuAtual(final Date data) {

    if (data == null) {
      return null;
    }
    return DataUtil.getProximoDiaUtilOuAtual(DataUtil.asLocalDateTime(data));
  }

  public static Date getProximoDiaUtilOuAtual(LocalDateTime data) {

    if (data == null) {
      return null;
    }
    while ((data.getDayOfWeek().equals(DayOfWeek.SATURDAY)
        || (data.getDayOfWeek().equals(DayOfWeek.SUNDAY)))) {

      data = data.plusDays(1l);
    }

    return DataUtil.asDate(data);
  }

  public static Date getUltimoDiaDoMes(final Date data) {

    if (data == null) {
      return null;
    }
    final LocalDate localDate = DataUtil.asLocalDate(data);
    return DataUtil.getUltimoDiaDoMes(localDate.getYear(), localDate.getMonthValue());
  }

  public static Date getUltimoDiaDoMes(final Integer ano, final Integer mes) {

    return DataUtil.montaData(ano, mes, DataUtil.getUltimoDiaDoMesAsInteger(ano, mes));
  }

  public static Integer getUltimoDiaDoMesAsInteger(final Integer year, final Integer month) {

    final LocalDate localDate = LocalDate.of(year, month, DataUtil.FIRST_DAY_OF_MONTH);

    return localDate.lengthOfMonth();
  }

  public static Boolean isDataBetween(
      final Date dataReferencia, final Date dataInicio, final Date dataFim) {

    if ((dataInicio == null) || (dataFim == null)) {
      return null;
    }
    return (dataReferencia.compareTo(dataInicio) >= 0) && (dataReferencia.compareTo(dataFim) <= 0);
  }

  public static boolean isDataFimMaiorDataInicio(final Date dataInicial, final Date dataFinal) {

    if ((dataInicial == null) || (dataFinal == null)) {
      return false;
    }
    return DataUtil.isDataFimMaiorDataInicio(
        DataUtil.asLocalDateTime(dataInicial), DataUtil.asLocalDateTime(dataFinal));
  }

  public static boolean isDataFimMaiorDataInicio(
      final LocalDateTime dataInicial, final LocalDateTime dataFinal) {

    if ((dataInicial == null) || (dataFinal == null)) {
      return false;
    }
    return dataFinal.isAfter(dataInicial);
  }

  public static boolean isDataFimMenorDataInicio(final Date dataInicial, final Date dataFinal) {

    if ((dataInicial == null) || (dataFinal == null)) {
      return false;
    }
    return DataUtil.isDataFimMenorDataInicio(
        DataUtil.asLocalDateTime(dataInicial), DataUtil.asLocalDateTime(dataFinal));
  }

  public static boolean isDataFimMenorDataInicio(
      final LocalDateTime dataInicial, final LocalDateTime dataFinal) {

    if ((dataInicial == null) || (dataFinal == null)) {
      return false;
    }
    return dataFinal.isBefore(dataInicial);
  }

  public static boolean isDataIgualComHora(final Date data1, final Date data2) {

    if ((data1 == null) || (data2 == null)) {
      return false;
    }
    return data1.equals(data2);
  }

  public static boolean isDataIgualSemHora(final Date data1, final Date data2) {

    if ((data1 == null) || (data2 == null)) {
      return false;
    }

    final LocalDateTime date1 =
        DataUtil.completarPrimeiraHoraDoDia(DataUtil.asLocalDateTime(data1));
    final LocalDateTime date2 =
        DataUtil.completarPrimeiraHoraDoDia(DataUtil.asLocalDateTime(data2));

    return date1.equals(date2);
  }

  public static boolean isDataInicioMaiorDataFim(final Date dataInicial, final Date dataFinal) {

    if ((dataInicial == null) || (dataFinal == null)) {
      return false;
    }
    return DataUtil.isDataInicioMaiorDataFim(
        DataUtil.asLocalDateTime(dataInicial), DataUtil.asLocalDateTime(dataFinal));
  }

  public static boolean isDataInicioMaiorDataFim(
      final LocalDateTime dataInicial, final LocalDateTime dataFinal) {

    if ((dataInicial == null) || (dataFinal == null)) {
      return false;
    }
    return dataInicial.isAfter(dataFinal);
  }

  public static boolean isDataMaiorDataAtual(final Date data) {

    if (data == null) {
      return false;
    }
    return DataUtil.isDataMaiorDataAtual(DataUtil.asLocalDateTime(data));
  }

  public static boolean isDataMaiorDataAtual(final LocalDateTime data) {

    if (data == null) {
      return false;
    }
    return data.isBefore(LocalDateTime.now());
  }

  public static boolean isDomingo(final Date data) {

    if (data == null) {
      return false;
    }
    return DataUtil.asLocalDate(data).getDayOfWeek().equals(DayOfWeek.SUNDAY);
  }

  public static boolean isFinalDeSemana(final Date data) {

    if (data == null) {
      return false;
    }
    final LocalDate localDate = DataUtil.asLocalDate(data);

    return localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)
        || localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
  }

  public static boolean isSabado(final Date data) {

    if (data == null) {
      return false;
    }
    return DataUtil.asLocalDate(data).getDayOfWeek().equals(DayOfWeek.SATURDAY);
  }

  public static String montaDataString(final Integer ano, final Integer mes, final Integer dia) {

    return ano + "-" + mes + "-" + dia;
  }

  public static Date montaData(
      final Integer ano, final Integer mes, final Integer dia, final boolean semHora) {

    if (semHora) {

      return DataUtil.asDate(LocalDate.of(ano, mes, dia));
    }

    return montaData(ano, mes, dia);
  }

  public static Date montaData(
      final Integer ano,
      final Integer mes,
      final Integer dia,
      final Integer hora,
      final Integer minuto,
      final Integer segundo) {

    return DataUtil.asDate(LocalDateTime.of(ano, mes, dia, hora, minuto, segundo, 0));
  }

  public static Date montaData(final Integer ano, final Integer mes, final Integer dia) {

    return DataUtil.asDate(LocalDateTime.of(ano, mes, dia, 0, 0, 0, 0));
  }

  public static Date parseDate(final String data, final String pattern) {

    try {
      return DataUtil.parseDateWhitException(data, pattern);
    } catch (final ParseException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static Date parseDateWhitException(final String data, final String pattern)
      throws ParseException {

    final SimpleDateFormat formatter = new SimpleDateFormat(pattern);

    return formatter.parse(data);
  }

  public static Date somaAnos(final Date data, final int anos) {

    if (data == null) {
      return null;
    }

    return DataUtil.asDate(DataUtil.somaAnos(DataUtil.asLocalDateTime(data), anos));
  }

  public static LocalDateTime somaAnos(final LocalDateTime data, final int anos) {

    return data.plusYears(anos);
  }

  public static Date somaDias(final Date data, final int dias) {

    if (data == null) {
      return null;
    }

    return DataUtil.asDate(DataUtil.somaDias(DataUtil.asLocalDateTime(data), dias));
  }

  public static LocalDateTime somaDias(final LocalDateTime data, final int dias) {

    if (data == null) {
      return null;
    }

    return data.plusDays(dias);
  }

  public static Date subtraiDias(final Date data, final int dias) {

    if (data == null) {
      return null;
    }

    return DataUtil.asDate(DataUtil.subtraiDias(DataUtil.asLocalDateTime(data), dias));
  }

  public static LocalDateTime subtraiDias(final LocalDateTime data, final int dias) {

    if (data == null) {
      return null;
    }

    return data.minusDays(dias);
  }

  public static Date somaMinutos(final Date data, final Long minutos) {

    if (data == null) {
      return null;
    }

    return DataUtil.asDate(DataUtil.somaMinutos(DataUtil.asLocalDateTime(data), minutos));
  }

  public static LocalDateTime somaMinutos(final LocalDateTime data, final Long minutos) {

    if (data == null) {
      return null;
    }

    return data.plusMinutes(minutos);
  }

  public static Date somaHoras(final Date data, final Long horas) {

    if (data == null) {
      return null;
    }

    return DataUtil.asDate(DataUtil.somaHoras(DataUtil.asLocalDateTime(data), horas));
  }

  public static LocalDateTime somaHoras(final LocalDateTime data, final Long horas) {

    if (data == null) {
      return null;
    }

    return data.plusHours(horas);
  }

  public static Date somaMeses(final Date data, final int meses) {

    if (data == null) {
      return null;
    }

    return DataUtil.asDate(DataUtil.somaMeses(DataUtil.asLocalDateTime(data), meses));
  }

  public static LocalDateTime somaMeses(final LocalDateTime data, final int meses) {

    if (data == null) {
      return null;
    }

    return data.plusMonths(meses);
  }

  public static Date subtraiMeses(final Date data, final int meses) {

    if (data == null) {
      return null;
    }

    return DataUtil.asDate(DataUtil.subtraiMeses(DataUtil.asLocalDateTime(data), meses));
  }

  public static LocalDateTime subtraiMeses(final LocalDateTime data, final int meses) {

    if (data == null) {
      return null;
    }

    return data.minusMonths(meses);
  }

  public static Date subtraiAnos(final Date data, final int anos) {

    if (data == null) {
      return null;
    }

    return DataUtil.asDate(DataUtil.subtraiAnos(DataUtil.asLocalDateTime(data), anos));
  }

  public static LocalDateTime subtraiAnos(final LocalDateTime data, final int anos) {

    if (data == null) {
      return null;
    }

    return data.minusYears(anos);
  }

  public static boolean validarDataAtualEntreDataIncioDataFim(
      final Date dataAtual, final Date dataInicio, final Date dataFim) {

    return DataUtil.validarDataAtualEntreDataIncioDataFim(
        DataUtil.asLocalDateTime(dataAtual),
        DataUtil.asLocalDateTime(dataInicio),
        DataUtil.asLocalDateTime(dataFim));
  }

  public static boolean validarDataAtualEntreDataIncioDataFim(
      final LocalDateTime dataAtual, final LocalDateTime dataInicio, final LocalDateTime dataFim) {

    return dataInicio.isBefore(dataAtual) && dataFim.isAfter(dataAtual);
  }

  public static Date getDataAtual() {

    return new Date();
  }

  public static Date asDate(final java.sql.Date data) {

    if (data == null) {
      return null;
    }

    return DataUtil.asDate(data.toLocalDate());
  }

  public static java.sql.Date asSqlDate(final Date data) {

    if (data == null) {
      return null;
    }

    return java.sql.Date.valueOf(DataUtil.asLocalDate(data));
  }

  public static java.sql.Timestamp asSqlTimeStamp(final Date data) {

    if (data == null) {
      return null;
    }

    return java.sql.Timestamp.valueOf(DataUtil.asLocalDateTime(data));
  }

  public static Object getFormattedValue(final Object dataAsString, final String pattern) {

    if (dataAsString == null) {
      return null;
    }

    return DataUtil.formataData(DataUtil.parseDate((String) dataAsString, pattern));
  }

  public static boolean isDateEquals(final Date prazoRealizado, final Date prazoTermino) {

    return prazoRealizado.compareTo(prazoTermino) == 0;
  }

  public static YearMonth getMesAno(final String mesAno) {

    final DateTimeFormatter format = DateTimeFormatter.ofPattern("MMuuuu");
    return YearMonth.parse(mesAno, format);
  }

  public static String getInicioMesAnoFormatado(final String mesAno) {

    final LocalDate primeiroDiaMesAno = getMesAno(mesAno).atDay(1);

    return primeiroDiaMesAno.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }

  public static String getFimMesAnoFormatado(final String mesAno) {

    final LocalDate ultimoDiaMesAno = getMesAno(mesAno).atEndOfMonth();

    return ultimoDiaMesAno.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }

  public static String getMes(final String mesAno) {

    final DateTimeFormatter format = DateTimeFormatter.ofPattern("MMuuuu");
    return YearMonth.parse(mesAno, format).format(DateTimeFormatter.ofPattern("MM"));
  }

  public static String getAno(final String mesAno) {

    final DateTimeFormatter format = DateTimeFormatter.ofPattern("MMuuuu");
    return YearMonth.parse(mesAno, format).format(DateTimeFormatter.ofPattern("uuuu"));
  }

  public static String formataData(final String anoMesDia) {

    final LocalDate localDate =
        LocalDate.parse(anoMesDia, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
  }

  public static String getHorasMinutosPorExtenso(final Integer minutos) {

    final Integer totalHoras = minutos / 60;
    final Integer totalMinutos = minutos % 60;

    final StringBuilder sb = new StringBuilder();
    sb.append(totalHoras);
    sb.append(totalHoras > 1 ? " horas" : " hora");

    if (totalMinutos > 0) {
      sb.append(" e " + totalMinutos);
      sb.append(totalMinutos > 1 ? " minutos" : " minuto");
    }

    return sb.toString();
  }
}
