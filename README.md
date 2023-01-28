# proiect-poo Ivan Iustin-Gabriel 323CB
    # Design Patterns
       ## Singleton
         Am folosit Singleton pentru a crea o singura instanta a clasei
         ProiectPOO deoarece nu are sens sa avem mai multe aplicatii de
         recomandare de muzica pornite in acelasi timp.
       ## Factory
         Am folosit Factory pentru a crea obiecte de tipul Streamer si User
         deoarece au multe atribute comune
       ## Builder
         L-am folosit pentru a crea obiecte de tipul Stream deoarece acestea
         se instantiaza diferit la citirea din fisier si la functia ADD
       ## Strategy
         Pentru functiile de RECOMMEND SI SURPRISE am folosit mai multe
         strategii pentru a recomanda ce e necesar(PODCAST, AUDIOBOOK, SONG)
       ## Facade
          Am folosit Facade pentru a crea o interfata simpla pentru
          citirea din fisierele de input si a crea obiectele necesare
          in cadrul aplicatiei(Streamer,Stream,User) cat si comenzile

    # Alte Mentiuni
      Pentru sortarea streamurilor am folosit un Dynamic Comparator deoarece
        pentru recomandari si surprize trebuie sa sortam streamurile pe 
        baza unor atribute diferite.