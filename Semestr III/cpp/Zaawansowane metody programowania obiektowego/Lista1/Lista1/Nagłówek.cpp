#include "Nag³ówek.h"


using namespace std;


void zmienDlugoscWektora(int &staraDlugosc, int nowaDlugosc, int *wartosci, int *offset, int *domyslna) {
	int przepiszDomyslna = *domyslna;
	przepiszTab(wartosci, staraDlugosc, nowaDlugosc, przepiszDomyslna);
	przepiszTab(offset, staraDlugosc, nowaDlugosc, przepiszDomyslna);
	staraDlugosc = nowaDlugosc;
}


//pomocnicza do przepisywania tablicy
void przepiszTab(int *staraTablica, int staraDlugosc, int nowaDlugosc, int domyslna) {
	int *nowaTablica = new int[nowaDlugosc];

	if (nowaDlugosc > staraDlugosc) {
		for (int i = 0; i < staraDlugosc; i++)
			nowaTablica[i] = staraTablica[i];

		for (int j = staraDlugosc; j < nowaDlugosc; j++)
			nowaTablica[j] = domyslna;
	}
	else {
		for (int i = 0; i < nowaDlugosc; i++) {
			nowaTablica[i] = staraTablica[i];
		}

		staraTablica = nowaTablica;
		delete[] staraTablica;
	}
}


bool ustalWartDomysl(int *staraDomyslna, int nowaDomyslna, int dlugosc, int *wartosci) {
	bool czyDomyslna = true;

	for (int i = 0; i < 2; i++) {
		//cout << wartosci[i] << ",syf ";
		if (wartosci[i] != *staraDomyslna)
			czyDomyslna = false;
	}
	if (czyDomyslna == true) {
		*staraDomyslna = nowaDomyslna;
		cout << endl << czyDomyslna << endl;
		wypelnijTablice(wartosci, nowaDomyslna, dlugosc);
	}
	return czyDomyslna;
}


void sortowanie(int *tablica, int dlugosc) {
	for (int i = 0; i < dlugosc; i++) {
		for (int j = 0; j < dlugosc - 1; j++) {
			if (tablica[j] > tablica[j + 1])
				swap(tablica[j], tablica[j + 1]);
		}
	}
}


void zmienKomorke(int *wartosci, int *offset, int *domyslna, int ktoraKomorka, int nowaWartoscKomorki, int dlugosc) {
	bool czyRzadka = false;
	if ((ktoraKomorka > dlugosc - 1) || ktoraKomorka < 0) {
		cout << "wartosc siega poza zakres wektora" << endl;
	}
	else {
		for (int i = 0; i < dlugosc; i++) {
			if ((ktoraKomorka - 1) == offset[i])
				czyRzadka = true;
		}


		if (czyRzadka && (nowaWartoscKomorki != *domyslna)) {
			//cout << "czyRzadka && (nowaWartoscKomorki != *domyslna)" << endl;
			bool przypisana = false;
			for (int i = 0; i < dlugosc; i++) {
				if (offset[i] == (ktoraKomorka - 1) && !przypisana) {
					offset[i] = ktoraKomorka - 1;
					wartosci[i] = nowaWartoscKomorki;
					przypisana = true;
				}
			}
		}

		else if (czyRzadka && (nowaWartoscKomorki == *domyslna)) {
			//cout << "czyRzadka && (nowaWartoscKomorki == *domyslna" << endl;

			//usunWartosc(offset, wartosci, dlugosc, ktoraKomorka);
			bool przypisana = false;
			for (int i = 0; i < dlugosc; i++) {
				if (offset[i] == (ktoraKomorka - 1) && !przypisana) {
					offset[i] = -1;
					wartosci[i] = *domyslna;
					przypisana = true;
				}


			}
		}
		else if (!czyRzadka && (nowaWartoscKomorki != *domyslna)) {
			//cout << "!czyRzadka && (nowaWartoscKomorki != *domyslna)" << endl;
			//dodajWartosc(offset, wartosci, dlugosc, ktoraKomorka, nowaWartoscKomorki);
			bool przypisana = false;
			for (int i = 0; i < dlugosc; i++) {
				if (offset[i] == -1 && !przypisana) {
					offset[i] = ktoraKomorka - 1;
					wartosci[i] = nowaWartoscKomorki;
					przypisana = true;
				}
			}

		}
		else {
			cout << "nic siê nie zmieni" << endl;
		}
	}
}


void wczytajWartRzadka(int *wartosci, int *offset, int *domyslna, int ktoraKomorka, int &dlugosc) {
	bool czyRzadka = false;
	int gdzieRzadka;

	if ((ktoraKomorka > dlugosc - 1) || ktoraKomorka < 0) {
		cout << "wartosc siega poza zakres wektora" << endl;
	}
	else {
		for (int i = 0; i < dlugosc; i++) {
			if ((ktoraKomorka - 1) == offset[i]) {
				czyRzadka = true;
				gdzieRzadka = i;
			}
		}
	}
	if (czyRzadka == true)
		cout << "wartosc: " << wartosci[gdzieRzadka] << endl;
	else
		cout << "wartosc domyslna: " << *domyslna << endl;
}


void wydrukuj(int *wartosci, int *offset, int dlugosc, int *domyslna) {
	bool zapisano = false;
	cout << "len: " << dlugosc << " values: ";

	for (int i = 0; i < dlugosc; i++) {
		zapisano = false;
		for (int j = 0; j < dlugosc; j++) {
			if (offset[j] == i) {
				cout << wartosci[j] << ", ";
				zapisano = true;
			}
		}
		if (!zapisano) {
			cout << *domyslna << ", ";
			zapisano = true;
		}
	}
	cout << endl;
}


void usunDynamiczna(int *wartosci, int *offset, int &dlugoscWektora, int *wartoscDomyslna, int&dlugoscTablic) {
	delete[] wartosci;
	delete[] offset;
	dlugoscWektora = 0;
	dlugoscTablic = 0;
	wartoscDomyslna = nullptr;
}


void usunWartosc(int *offset, int *wartosci, int &dlugosc, int ktoraKomorka) {
	int *noweWartosci = new int[dlugosc - 1];
	int *nowyOffset = new int[dlugosc - 1];
	int gdzieRzadka;

	for (int i = 0; i < (sizeof(offset) / sizeof(offset[0])); i++) {
		if ((ktoraKomorka - 1) == offset[i]) {
			gdzieRzadka = i;
		}
	}

	if (gdzieRzadka != 0) {
		memcpy(nowyOffset, offset, (gdzieRzadka - 1) * sizeof(int));
		memcpy(noweWartosci, offset, (gdzieRzadka - 1) * sizeof(int));
	}

	if (gdzieRzadka != (dlugosc - 1)) {
		memcpy(nowyOffset + gdzieRzadka, offset + gdzieRzadka + 1, (dlugosc - gdzieRzadka - 1) * sizeof(int));
		memcpy(noweWartosci + gdzieRzadka, offset + gdzieRzadka + 1, (dlugosc - gdzieRzadka - 1) * sizeof(int));
	}

	offset = nowyOffset;
	wartosci = noweWartosci;
	delete[] nowyOffset;
	delete[] noweWartosci;

}


void dodajWartosc(int *offset, int *wartosci, int &dlugosc, int ktoraKomorka, int wartoscKomorki) {
	int *noweWartosci = new int[dlugosc + 1];
	int *nowyOffset = new int[dlugosc + 1];
	int gdzieRzadka = 0;

	for (int i = 0; i < (sizeof(offset) / sizeof(offset[0])); i++) {
		if ((ktoraKomorka - 1) > offset[i]) {
			gdzieRzadka++;
		}
	}

	if (gdzieRzadka != 0) {
		memcpy(nowyOffset, offset, (gdzieRzadka) * sizeof(int));
		memcpy(noweWartosci, offset, (gdzieRzadka) * sizeof(int));
	}

	if (gdzieRzadka != (dlugosc - 1)) {
		memcpy(nowyOffset + gdzieRzadka + 1, offset + gdzieRzadka, (dlugosc - gdzieRzadka - 1) * sizeof(int));
		memcpy(noweWartosci + gdzieRzadka + 1, offset + gdzieRzadka, (dlugosc - gdzieRzadka - 1) * sizeof(int));
	}

	offset[gdzieRzadka] = ktoraKomorka;
	wartosci[gdzieRzadka] = wartoscKomorki;

	offset = nowyOffset;
	wartosci = noweWartosci;
	delete[] nowyOffset;
	delete[] noweWartosci;
}


/*
void powiekszWartOffset(int *wartosci, int *offset, int domyslna) {
int staraDlugosc = (sizeof(wartosci) / sizeof(wartosci[0]));
int nowaDlugosc = staraDlugosc * 2;
przepiszTab(wartosci, staraDlugosc, nowaDlugosc, domyslna);
przepiszTab(offset, staraDlugosc, nowaDlugosc, domyslna);
}
*/


void wypelnijTablice(int *tablica, int nowaWart, int dlugosc) {
	for (int i = 0; i < dlugosc; i++)
		tablica[i] = nowaWart;
}


void zainicjujWektor(int &dlugoscWektora, int *wartoscDomyslna, int *offsetTab, int *wartosciTab, int &dlugoscTablic, int dlugosc, int domyslna, int dlugoscTab) {
	dlugoscWektora = dlugosc;
	*wartoscDomyslna = domyslna;
	dlugoscTablic = dlugoscTab;
}


void wyswietlTab(int *tablica, int dlugosc) {
	for (int i = 0; i < dlugosc; i++)
		cout << tablica[i] << ", ";
}


void ZmienXpoczatkowychWartosciNaY(int *wartosci, int *offset, int zmienna, int zakres, int *domyslna, int dlugosc) {
	for (int i = 0; i <= zakres; i++) {
		zmienKomorke(wartosci, offset, domyslna, i, zmienna, dlugosc);
	}
}

void printHelp() {
	cout << "mvec <len> <def> - create new vector\n";
	cout << "len <len> - change vector length\n";
	cout << "def <off> <val> - set value\n";
	cout << "print – print?\n";
	cout << "del – delete everything\n";
	cout << "setDef <def> - set default\n";
	cout << "defStart <val> <number> changing value <number> beginning's values on value <val>";
	cout << "exit - exit\n";
}