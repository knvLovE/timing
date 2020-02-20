Краткое описание задачи:
Необходимо составить сводную таблицу по времени исполнения заявок в разрезе каждого исполнителя, посчитать норму дней и отклонение от нормы. Входной файл содержит все решенные инциденты за определенный период по всем исполнителям. Расчет отклонений реализовать програмно и через формулы Excel, что бы была возможность скорректировать норму дней. 
Реализация:<br/>
1.Для работы с Excel используется библиотека org.apache.poi (poi-ooxml) <br/>
2.На вход подается файл in.xlsx в той же папке, что и программа (выгрузка из SM, в данном примере данные обезличены).<br/>
3.Из файла считываются значения полей: Инициатор, Исполнитель, Дата завершения работ, #time (время на закрытие заявки).<br/>
4.Если такие поля не найдены, предусмотрены информационные сообщения.
5.Свои собственные бизнес исключения не использовал, т.к. текст вывода должен быть лаконичный. Отображение сообщений происходит в классе MainWindow через swing.<br/>
6.Далее расчет разбивается на три блока:<br/>
1ый блок: рассчитывается суммарное время на закрытие в разрезе каждого исполнителя. Если исполнитель назначал заявку сам на себя – то это считается как время, отведенное на проектные работы.<br/>
2ой блок: По всем датам заявок вычисляется норма дней (количество дней исключая субботы и воскресенье без повторений).
Вычисляется отклонение от нормы по каждому работнику.<br/>
3ий блок: Добавляется шапка с периодом выполенных заявок (минимальная и максимальная дата в файле) и информацией о необходимости скорректировать норму в соответствии с табелем рабочего времени. (это используется для рассылки во внутренней почте).<br/>
Формируется итоговый файл out.xlsx<br/>
