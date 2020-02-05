Краткое описание задачи:
На вход подается файл in.xlsx (выгрузка из SM, данные обезличены)
Из файла считываются значения полей: Инициатор, Исполнитель, Дата завершения работ, #time (время на закрытие).
Если такие поля не найдены, прусмотрены информационные сообщения. (Не исключения, т.к. текст должен быть лаконичный).
Далее рассчитывается суммарное время на закрытие в разрезе каждого исполнителя. Если исполнитель назначал заявку сам на себя – то это считается как время, отведенное на проектные работы.
По датам вычисляется норма дней (количество дней исключая субботы и воскресенье).
Вычисляется отклонение от нормы по каждому работнику.
Добавляется шапка с информацией о необходимости скорректировать норму в соответствии с табелем рабочего времени. (это используется для рассылки).
Формируется итоговый файл out.xlsx  
