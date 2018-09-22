Public Const FD As String = "dd/mm/yyyy"


'Процедура для придания XML читабельного вида (с отступами)
Sub transformXML(ByRef xml As Variant)
    'Cоздание объекта XSL
    Set xsl = CreateObject("MSXML2.DOMDocument")
    ' Msxml2.DOMDocument.6.0

    'Загрузка XSL из строки (не требует наличия отдельного XSL-файла)
    xsl.LoadXML ("<xsl:stylesheet version='1.0' xmlns:xsl='http://www.w3.org/1999/XSL/Transform'>" & vbCrLf & _
    "<xsl:output method='xml' version='1.0' encoding='UTF-8' indent='yes'/>" & vbCrLf & _
    "<xsl:template match='@*|node()'>" & vbCrLf & _
    "<xsl:copy>" & vbCrLf & _
    "<xsl:apply-templates select='@*|node()' />" & vbCrLf & _
    "</xsl:copy>" & vbCrLf & _
    "</xsl:template>" & vbCrLf & _
    "</xsl:stylesheet>")


    'Выполнение преобразования
    xml.transformNodeToObject xsl, xml
End Sub

Function createEl(ByRef xml As Variant, ByVal name As String, ByVal val As String) As Variant
        Set r = xml.createElement(name)
        If Not IsEmpty(val) Then
            r.Text = val
        End If
        Set createEl = r
End Function

Function createEl0(ByRef xml As Variant, ByVal name As String) As Variant
        Set createEl0 = xml.createElement(name)
End Function

Function formatDate(d As Variant) As String
    If Not IsEmpty(d) Then
        Debug.Print Replace(Format(d, FD), ".", "/")
        r = Replace(Format(d, FD), ".", "/")
    Else
        r = ""
    End If
    formatDate = r
End Function

Function createИнфКлиент(ByRef xml As Variant, ByRef ws As Worksheet, ByVal rowNum As Integer) As Variant
    Set r = xml.createElement("ИнфКлиент")

    r.appendChild (createEl(xml, "КлиентАктив", "1"))

    contractDate = formatDate(ws.Cells(rowNum, 4).Value)

    r.appendChild (createEl(xml, "ДатаИдент", contractDate))

    r.appendChild (createEl(xml, "ТипКлиента", "2"))

    r.appendChild (createEl(xml, "ПризнакРезидент", "1"))

    r.appendChild (createEl(xml, "ПризнакКонтрагент", "0"))

    Set сведОрг = createEl0(xml, "СведОрг")
    r.appendChild (сведОрг)

    Set СведенияФЛИП = createEl0(xml, "СведенияФЛИП")
    сведОрг.appendChild (СведенияФЛИП)

    ' добавляем ФИО
    Set ФИОФЛИП = createEl0(xml, "ФИОФЛИП")
    ФИОФЛИП.appendChild (createEl(xml, "Фам", ws.Cells(rowNum, 7).Value))
    ФИОФЛИП.appendChild (createEl(xml, "Имя", ws.Cells(rowNum, 7).Value))
    ФИОФЛИП.appendChild (createEl(xml, "Отч", ws.Cells(rowNum, 7).Value))
    СведенияФЛИП.appendChild (ФИОФЛИП)

    СведенияФЛИП.appendChild (createEl(xml, "ИННФЛИП", ws.Cells(rowNum, 9).Value))

    СведенияФЛИП.appendChild (createEl(xml, "КодОКСМ", "643"))

    СведенияФЛИП.appendChild (createEl(xml, "СтранаНаименование", "Россия"))
    СведенияФЛИП.appendChild (createEl(xml, "ДатаРождения", formatDate(ws.Cells(rowNum, 8).Value)))

    Set МестоРожд = createEl0(xml, "МестоРожд")
    МестоРожд.appendChild (createEl(xml, "КодОКСМ", vbNullString))
    МестоРожд.appendChild (createEl(xml, "КодСубъектаПоОКАТО", vbNullString))
    МестоРожд.appendChild (createEl(xml, "Район", vbNullString))
    МестоРожд.appendChild (createEl(xml, "Пункт", vbNullString))
    СведенияФЛИП.appendChild (МестоРожд)

    СведенияФЛИП.appendChild (createEl(xml, "ВидГражданства", "1"))

    Set СведДокУдЛичн = createEl0(xml, "СведДокУдЛичн")
    СведДокУдЛичн.appendChild (createEl(xml, "ВидДокКод", "10"))
    СведДокУдЛичн.appendChild (createEl(xml, "ВидДокНаименование", "Паспорт РФ"))
    СведДокУдЛичн.appendChild (createEl(xml, "СерияДок", ws.Cells(rowNum, 10).Value))
    СведДокУдЛичн.appendChild (createEl(xml, "НомДок", ws.Cells(rowNum, 11).Value))
    СведДокУдЛичн.appendChild (createEl(xml, "ДатВыдачиДок", formatDate(ws.Cells(rowNum, 12).Value)))
    СведДокУдЛичн.appendChild (createEl(xml, "КемВыданДок", ws.Cells(rowNum, 14).Value))
    СведДокУдЛичн.appendChild (createEl(xml, "КодПодр", ws.Cells(rowNum, 13).Value))
    'СведДокУдЛичн.appendChild (createEl(xml, "ИноеНаименованиеДок", ""))
    СведенияФЛИП.appendChild (СведДокУдЛичн)

    Set СведМигрКарта = createEl0(xml, "СведМигрКарта")
    СведМигрКарта.appendChild (createEl(xml, "СерияДок", ""))
    СведМигрКарта.appendChild (createEl(xml, "НомДок", ""))
    СведМигрКарта.appendChild (createEl(xml, "ДатаНачала", ""))
    СведМигрКарта.appendChild (createEl(xml, "ДатаОкончания", ""))
    СведенияФЛИП.appendChild (СведМигрКарта)

    Set СведДокПраво = createEl0(xml, "СведДокПраво")
    СведДокПраво.appendChild (createEl(xml, "ВидДокКод", ""))
    СведДокПраво.appendChild (createEl(xml, "СерияДок", ""))
    СведДокПраво.appendChild (createEl(xml, "НомДок", ""))
    СведДокПраво.appendChild (createEl(xml, "ДатаНачала", ""))
    СведДокПраво.appendChild (createEl(xml, "ДатаОкончания", ""))
    СведенияФЛИП.appendChild (СведДокПраво)


    СведенияФЛИП.appendChild (createEl(xml, "ПризнакПринПубЛицо", "0"))
    'СведенияФЛИП.appendChild (createEl(xml, "ПризнакРоссПубЛицо", "0"))
    'СведенияФЛИП.appendChild (createEl(xml, "ПризнакИнострПубЛицо", "0"))
    'СведенияФЛИП.appendChild (createEl(xml, "ИнойПризнак", "0"))
    'СведенияФЛИП.appendChild (createEl(xml, "ВидИдентификации", "2"))
    СведенияФЛИП.appendChild (createEl(xml, "СНИЛСФЛИП", ""))

    r.appendChild (createEl(xml, "Телефон", ws.Cells(rowNum, 15).Value))

    Set АдрРег = createEl0(xml, "АдрРег")
    АдрРег.appendChild (createEl(xml, "КодОКСМ", ""))
    АдрРег.appendChild (createEl(xml, "СтранаНаименование", ""))
    АдрРег.appendChild (createEl(xml, "Индекс", ""))
    АдрРег.appendChild (createEl(xml, "КодСубъектаПоОКАТО", ""))
    АдрРег.appendChild (createEl(xml, "Район", ""))
    АдрРег.appendChild (createEl(xml, "Пункт", ws.Cells(rowNum, 16).Value))
    АдрРег.appendChild (createEl(xml, "Улица", ""))
    АдрРег.appendChild (createEl(xml, "Дом", ""))
    АдрРег.appendChild (createEl(xml, "Корп", ""))
    АдрРег.appendChild (createEl(xml, "Оф", ""))
    r.appendChild (АдрРег)

    Set АдрПреб = createEl0(xml, "АдрПреб")
    АдрПреб.appendChild (createEl(xml, "КодОКСМ", ""))
    АдрПреб.appendChild (createEl(xml, "СтранаНаименование", ""))
    АдрПреб.appendChild (createEl(xml, "Индекс", ""))
    АдрПреб.appendChild (createEl(xml, "КодСубъектаПоОКАТО", ""))
    АдрПреб.appendChild (createEl(xml, "Район", ""))
    АдрПреб.appendChild (createEl(xml, "Пункт", ws.Cells(rowNum, 16).Value))
    АдрПреб.appendChild (createEl(xml, "Улица", ""))
    АдрПреб.appendChild (createEl(xml, "Дом", ""))
    АдрПреб.appendChild (createEl(xml, "Корп", ""))
    АдрПреб.appendChild (createEl(xml, "Оф", ""))

    r.appendChild (АдрПреб)

    r.appendChild (createEl(xml, "ПризнакИдентКлиента", "1"))

    r.appendChild (createEl(xml, "ДатаНачалоОтн", contractDate))

    r.appendChild (createEl(xml, "ДатаЗаполнения", formatDate(now)))

    r.appendChild (createEl(xml, "ИнфСтепеньРиск", "Нет критериев для присвоения иного уровня риска"))

    r.appendChild (createEl(xml, "ПаспортВалид", "1"))

    r.appendChild (createEl(xml, "ИнфЦельОтношения", "Страхование жизни"))

    r.appendChild (createEl(xml, "ИнфХарактерОтношения", "Долгосрочные"))

    r.appendChild (createEl(xml, "ИнфЦельФХД", "Страхование жизни"))

    r.appendChild (createEl(xml, "ИнфРепутация", "Устойчивая"))

    r.appendChild (createEl(xml, "ИнфФинансы", "Устойчивое"))

    r.appendChild (createEl(xml, "ИнфПроисхождениеДеньги", "Личные накопления"))

    Set fio = createEl0(xml, "ФИОСотрудника")

    fio.appendChild (createEl(xml, "Фам", "Королев"))

    fio.appendChild (createEl(xml, "Имя", "Сергей"))

    fio.appendChild (createEl(xml, "Отч", "Геннадьевич"))

    r.appendChild (fio)

    r.appendChild (createEl(xml, "ДолжностьСотрудника", "Главный специалист УОП"))

    r.appendChild (createEl(xml, "СтепеньРиска", "1"))

    Set createИнфКлиент = r
End Function

Sub exportXML()
    'Путь для сохранения итогового XML
    ' xmlFile = ActiveWorkbook.Path & "\export.xml"
    xmlFile = "c:\temp\export.xml"

    'Cоздание объекта XML
    Set xml = CreateObject("MSXML2.DOMDocument")
    'Добавление описания XML
    xml.appendChild xml.createProcessingInstruction("xml", "version='1.0' encoding='utf-8'")

    'Добавление корневого элемента "company"
    Set Root = xml.createElement("СведКлиент")
    xml.appendChild (Root)

    data_row = 2

    'Цикл по строкам (пока не встретится строка с пустым "Порядковым номером")
    Do While Not IsEmpty(Cells(data_row, 1))
       'Вызов функции добавления сотрудника компании
        Root.appendChild (createИнфКлиент(xml, ActiveSheet, data_row))
        'Переход к следующей строке таблицы
        data_row = data_row + 1
    Loop

    'Выполнение XSL-преобразования для добавления отступов в XML
    Call transformXML(xml)

    'Сохранение файла (без выбора пути сохранения, удобно при отладке)
    xml.Save xmlFile
    MsgBox "Export complete!"
    'Сохранение файла (с выбором пути сохранения)
    'xml.Save Application.GetSaveAsFilename(xmlFile, "Файл экспорта (*.xml),", , "Введите имя файла", "Сохранить")
End Sub
