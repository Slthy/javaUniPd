# Esercizio 11
## Argomento: un esercizio per imparare a pensare "out-of-the-box"
### Progettare il programma *PrintSelectedMonth* che chieda all'utente un numero intero compreso tra 1 e 12 e visualizzi il nome del mese corrispondente nella forma:
```shell
Inserisci il numero del mese (1-12): 5
Maggio
```
mesi:
- 1:
  - index: 0
  - nome: gennaio
  - caratteri: 7
- 2:
  - index:1
  - nome:febbraio
  - caratteri:8
- 3:
  - index:2
  - nome:marzo
  - caratteri:5
- 4:
  - index:3
  - nome:aprile
  - caratteri:6
- 5:
  - index:4
  - nome:maggio
  - caratteri:6
- 6:
  - index:5
  - nome:giugno
  - caratteri:6
- 7:
  - index:6
  - nome:luglio
  - caratteri:6
- 8:
  - index:7
  - nome:agosto
  - caratteri:6
- 9:
  - index:8
  - nome:settembre
  - caratteri:9
- 10:
  - index:9
  - nome:ottobre
  - caratteri:7
- 11:
  - index:10
  - nome:novembre
  - caratteri:8
- 12:
  - index:11
  - nome:dicembre
  - caratteri:8

idee:
- operatori terziari
- 

pseudo-codice:
- definire stringa (final String)*monthLenList*="78566666978"
- definire stringa (final String)*monthNamesList*=```gennaio***febbraio**marzo*****aprile****maggio****giugno****luglio****agosto****settembre*ottobre***novembre**dicembre**```
- chiedi all'utente (int)*month*
- month -- (to index)
- 