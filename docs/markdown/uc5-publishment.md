# ΠΧ5. Δημοσιοποίηση Σύνθεσης
**Πρωτεύων Actor**: Πελάτης  
**Ενδιαφερόμενοι:**
- **Πελάτης**: Θέλει να δημοσιοποιεί τις ολοκληρωμένες συνθέσεις που αγόρασε. 

**Προϋποθέσεις**: Ο Πελάτης έχει εκτελέσει με επιτυχία την περίπτωση χρήσης "Αγορά Προϊόντων"


## Βασική Ροή

### Α) Δημοσιοποίηση Σύνθεσης
   
1. Ο Πελάτης ολοκληρώνει την αγορά σύνθεσης.
2. Ο Πελάτης επιλέγει δημοσιοποίηση σύνθεσης. 
3. Το Σύστημα συλλέγει τα στοιχεία της σύνθεσης.
4. Το Σύστημα "ανεβάζει" την ολοκληρωμένη σύνθεση του πελάτη στην πλατφόρμα.



**Εναλλακτικές Ροές**

*2α. Η επιθυμία του πελάτη για δημοσιοποίηση της σύνθεσης του  δεν  υφίσταται.*
1. Ο Πελάτης επιλέγει επιστροφή στην αρχική. 
2. Η ΠΧ τερματίζει.


*4α Σφάλμα συστήματος κατα το ανέβασμα*
1. Το Σύστημα εμφανιζει κατάλληλο μήνυμα
2. Ο πελάτης επιλέγει επιστροφή και η ΠΧ επιστρέφει στο βήμα 2.


*Σε οποιοδήποτε σημείο το λογισμικό καταρρέει.
1. Ο Πελάτης επανεκκινεί την εφαρμογή.
2. Η Διαδικασία επιστρέφει στο βήμα 2 της ΠΧ1.




## Διάγραμμα
![Διάγραμμα]()