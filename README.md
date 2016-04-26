# MovieManager
Movie file database

scant filmdateien (unterordner inkl)
	-> mkv dateiinfos rauslesen
		-> dateigrösse, filmlänge, auflösung, sprachen, subs, audiotracks,
		   bitrate, framerate, codec
	-> tmdb filminfos mit dateiname suchen (filmname, jahr)
		-> bewertung, inhalt, cover (braucht viel platz),

filmdatei zur datenbank hinzufügen
	-> erstes suchresultat als default nehmen
	-> wenn filmdatei in datenbank existiert dann ersetzungsanfrage beginnen
		-> ersetzungsgrund als loginfo mitgeben (besser quali, fehlerhaft , usw)

filmdatenbank oberfläche
	-> sortieren & filtern nach allen verfügbaren daten
	-> zusammenfassungen
		-> anzahl filme, gesamtgrösse, gesamtdauer usw

Serien & Dokus als extra daten
