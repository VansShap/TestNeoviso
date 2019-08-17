# TestNeoviso

Comments:
Класс, с которого стартует приложение - Runner.

Первоначально, нужно изменить настройки в src/main/resources/hibernate.cfg.xml (сменить username и password для доступа к PostgreSQL).
Тоже самое в src/main/java/utils/DBConnector и src/main/java/utils/DBCreator (через них происходит создание БД, с которой работает  приложение)

В остальном менять ничего не нужно.

После запуска Runner будет авторизация (я занес 2 User-ов в БД(пара username(password)): readman(br991) и Headread1(abaz7))
Также в БД будут занесены 2 магазина и 4 вида книг распределенных в разных количествах по магазинам