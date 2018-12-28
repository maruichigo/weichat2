https://blog.csdn.net/babylove_BaLe/article/details/73201115

'File OutPut
    Set Fileout = fs.CreateTextFile(ThisWorkbook.Path & "\" & Cells(1, 1) & "BeanTest.java", True, True)
    For i = 2 To 100
      If InStr(Sheets(2).Cells(i, 1), "package") > 0 Then
        Fileout.WriteLine Sheets(2).Cells(i, 1)
        Fileout.WriteLine ""
        Fileout.WriteLine "import static org.junit.Assert.assertEquals;"
        Fileout.WriteLine "import static org.mockito.Mockito.doNothing;"
        Fileout.WriteLine "import static org.mockito.Mockito.when;"
        Fileout.WriteLine "import org.junit.After;"
        Fileout.WriteLine "import org.junit.Before;"
        Fileout.WriteLine "import org.junit.Test;"
        Fileout.WriteLine "import org.mockito.ArgumentCaptor;"
        Fileout.WriteLine "import org.mockito.InjectMocks;"
        Fileout.WriteLine "import org.mockito.Mock;"
        Fileout.WriteLine "import static org.mockito.Mockito.doThrow;"
        Fileout.WriteLine "import org.mockito.MockitoAnnotations;"
      End If
      
      If InStr(Sheets(2).Cells(i, 1), "import") > 0 Then
        Fileout.WriteLine Sheets(2).Cells(i, 1)
      End If
      
      'Title
      If InStr(Sheets(2).Cells(i, 1), "*") > 0 And InStr(Sheets(2).Cells(i, 1), "@author") > 0 Then
        Fileout.WriteLine ""
        Fileout.WriteLine Sheets(2).Cells(i - 3, 1)
        Fileout.WriteLine Sheets(2).Cells(i - 2, 1)
        Fileout.WriteLine Sheets(2).Cells(i - 1, 1)
        Fileout.WriteLine Sheets(2).Cells(i, 1)
        Fileout.WriteLine Sheets(2).Cells(i + 1, 1)
        Fileout.WriteLine Sheets(2).Cells(i + 2, 1)
        Fileout.WriteLine "public class " & UCase(Cells(1, 1)) & "BeanTest {"
        Fileout.WriteLine ""
        Fileout.WriteLine "    // テストTarget"
        Fileout.WriteLine "    @InjectMocks"
        Fileout.WriteLine "    private " & UCase(Cells(1, 1)) & "Bean target;"
        Fileout.WriteLine "    // Mockitoオブジェクト"
      End If
    Next i
    
    'Mock
    int_datarow = 1
      Do While Sheets(3).Cells(int_datarow, 1) <> ""
      Fileout.WriteLine "    @Mock"
      Fileout.WriteLine "    private " & Trim(Sheets(3).Cells(int_datarow, 1))
      int_datarow = int_datarow + 1
    Loop
      
    Fileout.WriteLine ""
    Fileout.WriteLine "    public MST012BeanTest() {"
    Fileout.WriteLine "}"
    Fileout.WriteLine ""
    
    Fileout.WriteLine "    @Before"
    Fileout.WriteLine "    public void setUp() {"
    Fileout.WriteLine "        // Mockitoオブジェクト初期化"
    Fileout.WriteLine "        MockitoAnnotations.initMocks(this);"
    Fileout.WriteLine "}"
    Fileout.WriteLine ""
    Fileout.WriteLine "    @After"
    Fileout.WriteLine "    public void tearDown() {"
    Fileout.WriteLine "}"
    Fileout.WriteLine ""
    
    
    
    Fileout.Close
