#include "Nag³ówek.h"

using namespace std;

#define DLUGOSC_STALA 50
#define DLUGOSC_TABLIC 10
#define OFFSET_PUSTY -1
#define WARTOSCI_PUSTY 0
#define WARTOSC_DOMYSLNA 0

//zadefiniowaæ sta³e do interfejsu(stringi)
//usun¹æ cout z warstwy obliczeniowej
//wywaliæ int[o] w mainie bo to nic nie robi
//tablica ma byæ ma³a
//


int main() {

	int dlugoscWektora = DLUGOSC_STALA;
	int *wartoscDomyslna = new int();
	int *offsetTab = NULL;
	int *wartosciTab = NULL;

	zainicjujWektor(dlugoscWektora, wartoscDomyslna, offsetTab, wartosciTab, DLUGOSC_STALA, WARTOSC_DOMYSLNA);
	offsetTab = new int[DLUGOSC_TABLIC];
	wartosciTab = new int[DLUGOSC_TABLIC];

	wypelnijTablice(wartosciTab, WARTOSC_DOMYSLNA, dlugoscWektora);
	wypelnijTablice(offsetTab, OFFSET_PUSTY, dlugoscWektora);




	//-------------INTERFEJS------------


	printHelp();
	/*
	int* values;
	int* offsets;

	int defaultValue;
	int vectorLength;

	//int arrayLength = INIT_ARRAY_LENGTH;
	*/



	bool memoryFreed = true;

	while (1) {
		cout << ">>> ";

		string s;
		cin >> s;

		if ("mvec" == s) {
			if (!memoryFreed) {
				usunDynamiczna(wartosciTab, offsetTab, dlugoscWektora, wartoscDomyslna);
			}
			int dlugoscNowa;
			int domyslnaNowa;
			cin >> dlugoscNowa >> domyslnaNowa;
			/*
			int dlugoscWektora = DLUGOSC_STALA;
			int *wartoscDomyslna = new int();
			int *offsetTab = new int[0];
			int *wartosciTab = new int[0];
			*/
			zainicjujWektor(dlugoscWektora, wartoscDomyslna, offsetTab, wartosciTab, dlugoscNowa, domyslnaNowa);

			offsetTab = new int[dlugoscWektora];
			wartosciTab = new int[dlugoscWektora];

			wypelnijTablice(wartosciTab, WARTOSCI_PUSTY, dlugoscWektora);
			wypelnijTablice(offsetTab, OFFSET_PUSTY, dlugoscWektora);

			ustalWartDomysl(wartoscDomyslna, domyslnaNowa, dlugoscWektora, wartosciTab);

			memoryFreed = false;

		}
		else if ("len" == s) {
			int newLength;
			cin >> newLength;

			zmienDlugoscWektora(dlugoscWektora, newLength, wartosciTab, offsetTab, wartoscDomyslna);
			//wartosc domyslna jest bez wskaŸnika w prototypie
			dlugoscWektora = newLength;
		}
		else if ("def" == s) {
			if (memoryFreed) {
				cout << "Vector not initialized\n";
			}
			else {
				int insertOffset, insertValue;
				cin >> insertOffset >> insertValue;

				if (insertOffset >= dlugoscWektora) {
					cout << "Offset out of bounds\n";
				}
				else if (insertOffset < 0) {
					cout << "Index cannot be <0\n";
				}
				else {
					zmienKomorke(wartosciTab, offsetTab, wartoscDomyslna, insertOffset, insertValue, dlugoscWektora);

					//arrayLength = insert(values, offsets, arrayLength, defaultValue, insertOffset, insertValue);
				}
			}
		}
		else if ("print" == s) {
			if (memoryFreed) {
				cout << "Vector not initialized\n";
			}
			else {
				wydrukuj(wartosciTab, offsetTab, dlugoscWektora, wartoscDomyslna);
				/*
				cout << endl;
				wyswietlTab(wartosciTab, dlugoscWektora);
				cout << endl;
				wyswietlTab(offsetTab, dlugoscWektora);
				cout << endl;
				*/

				//cout << toString(values, offsets, vectorLength, arrayLength, defaultValue);
			}
		}
		else if ("del" == s) {
			if (memoryFreed) {
				cout << "Vector not initialized\n";
			}
			else {
				memoryFreed = true;
				usunDynamiczna(wartosciTab, offsetTab, dlugoscWektora, wartoscDomyslna);
			}
		}
		else if ("setDef" == s) {
			int def;
			cin >> def;

			if (ustalWartDomysl(wartoscDomyslna, def, dlugoscWektora, wartosciTab)) {

				//if (allDefault(values, offsets, arrayLength, defaultValue)) {
				*wartoscDomyslna = def;
			}
			else {
				cout << "Array is not empty\n";
			}
		}
		else if ("exit" == s) {
			if (!memoryFreed) {
				usunDynamiczna(wartosciTab, offsetTab, dlugoscWektora, wartoscDomyslna);
			}
			exit(0);
		}
		//------DODATEK-----
		else if ("defStart" == s) {
			int wartoscZapelniajaca;
			int dlugoscZapelniania;
			cin >> wartoscZapelniajaca >> dlugoscZapelniania;

			if (dlugoscZapelniania >= dlugoscWektora) {
				cout << "Za dluga wartosc zapelniania." << endl;
			}
			else {
				ZmienXpoczatkowychWartosciNaY(wartosciTab, offsetTab, wartoscZapelniajaca, dlugoscZapelniania, wartoscDomyslna, dlugoscWektora);
			}

		}
		else {
			cout << "Unrecognized command\n";
		}
	}

	system("pause");
	return 0;
}
