#include <iostream>
#include <vector>
#include <sstream>

#include "SparseMatrix.h"

using namespace std;

#define ADDMAT "addmat"
#define LIST   "list"
#define DEL	   "del"
#define DELALL "delall"
#define DEF	   "def"
#define PRINT  "print"
#define CLONE  "clone"
#define RENAME "rename"
#define CHDEF "chdef"
#define DEFSWAP "defswap"
#define EXIT   "exit"


#define UNCCOMM   "Unrecognized command\n"
#define INCCOMM	  "incorrect input\n"
#define NOTADD   "value is not added\n"




void printHelp() {
	cout << "addmat <dimNum>  <dim0size> ... <dimNum-1size> <def> <!name!> - new SparseMatrix\n";
	cout << "list - print objects from Vector matrixArr\n";
	cout << "del <matoff> - deleting object in Vector on matoff\n";
	cout << "delall - deleting all objects in vector\n";
	cout << "def<matoff> <dim1> ... <dimNum-1> <val> - set value in object\n";
	cout << "print <matoff> - printing values on object\n";
	cout << "clone <matoff> - cloning object and adding to vector\n";
	cout << "rename <matoff>  <newName> - changing object name\n";
	cout << "chdef <matoff> <newdef> - changing default value\n";
	cout << "defswap <matoff> <newdef> - changing default value and add old value to Matrix\n";
	cout << "exit - exit the program\n";
}


int main() {
	std::vector < SparseMatrix* > matrixArr;

	printHelp();


	
	while (1) {
		cout << ">>>";

		//cout << matrixArr.size();

		string s;
		cin >> s;

		if (s == ADDMAT) {
			int dym;
			int *rngVal;
			int defVal;

			cin >> dym;

			rngVal = new int[dym];

			for (int i = 0; i < dym; i++) {
				int val;
				cin >> val;
				rngVal[i] = val;

			}
			// addmat 1 5 0
			// def 0 0 1
			// def 0 3 1
			// def 0 4 2
			// def 0 6 1
			// defswap 0 1
			cin >> defVal;

			string  n;
			//cin >> n;

			if (cin.get() == '\n') {

				//SparseMatrix(int dym, int rngVal[], int defVal, string nam)
				matrixArr.push_back(new SparseMatrix(dym, rngVal, defVal));
			}
			else {
				cin >> n;
				matrixArr.push_back(new SparseMatrix(dym, rngVal, defVal, n));
			}



			delete[] rngVal;
		}
		else if (s == LIST) {

			cout << matrixArr.size() << " matrices: \n";

			for (int i = 0; i < matrixArr.size(); i++) {
				cout << "[ " << i << " ] - ";
				cout << matrixArr.at(i)->matrixToString();
				cout << endl;
			}
		}
		else if (s == DEL) {
			int offset;
			cin >> offset;


			if (offset < 0 || offset >= matrixArr.size())
				cout << INCCOMM << endl;
			else {
				matrixArr.erase(matrixArr.begin() + (offset));
				//delete matrixArr.at(offset);
				//delete matrixArr[offset];

				/*
				std::vector< SparseMatrix* >::iterator it = matrixArr.erase(matrixArr.begin() + (offset));
				if (it != matrixArr.end()) {
					delete (*it);
				}
				else cout << "error" << endl;
				*/

			}
		}
		else if (s == DELALL) {

			for (std::vector< SparseMatrix* >::iterator it = matrixArr.begin(); it != matrixArr.end(); ++it) {
				delete (*it);
			}
			matrixArr.clear();

			//matrixArr.clear();
			//delete[] matrixArr;
		}
		else if (s == DEF) {
			int offset;
			cin >> offset;


			if (offset < 0 || offset >= matrixArr.size())
				cout << INCCOMM << endl;
			else {

				int dmn = matrixArr.at(offset)->getDymensions();

				int *crd;
				crd = new int[dmn];
				bool correct = true;

				for (int i = 0; i < dmn; i++) {
					int val;
					cin >> val;
					if (val < 0 || val > matrixArr.at(offset)->getRangeAt(i)) {
						cout << INCCOMM << endl;
						correct = false;
					}
					crd[i] = val;
				}

				int v;
				cin >> v;
				if (correct)
					matrixArr.at(offset)->setValue(v, crd);
				else
					cout << NOTADD << endl;
				delete[] crd;
			}

		}
		else if (s == PRINT) {
			int offset;
			cin >> offset;


			if (offset < 0 || offset >= matrixArr.size())
				cout << INCCOMM << endl;
			else
				matrixArr.at(offset)->toString();
		}
		else if (s == CLONE) {
			int offset;
			cin >> offset;


			if (offset < 0 || offset >= matrixArr.size())
				cout << INCCOMM << endl;
			else
				matrixArr.push_back(new SparseMatrix(*matrixArr.at(offset)));
		}
		else if (s == RENAME) {
			int offset;
			string newN;
			cin >> offset >> newN;


			if (offset < 0 || offset >= matrixArr.size() || !newN.empty())
				cout << INCCOMM << endl;
			else
				matrixArr.at(offset)->setName(newN);
		}
		else if (s == CHDEF) {
			int offset;
			int newD;
			cin >> offset >> newD;


			if (offset < 0 || offset >= matrixArr.size())
				cout << INCCOMM << endl;
			else
				matrixArr.at(offset)->changeDefaultValue(newD);
		}

		else if (s == DEFSWAP) {
			int offset;
			int newD;
			cin >> offset >> newD;


			if (offset < 0 || offset >= matrixArr.size())
				cout << INCCOMM << endl;
			else
				matrixArr.at(offset)->defSwap(newD);
		}
		else if (s == EXIT) {

			for (std::vector< SparseMatrix* >::iterator it = matrixArr.begin(); it != matrixArr.end(); ++it) {
				delete (*it);
			}

			exit(0);
		}

		else
			cout << UNCCOMM;
	}
	system("pause");
	return 0;
}

/*
int array[3] = { 10, 20, 30 };
SparseMatrix sm1(3, 0, "jakas", array);

int coord1[3] = { 1, 2, 3 };
int coord2[3] = { 2, 3, 5 };
int coord3[3] = { 3, 6, 3 };
sm1.addNewValue(1, coord1);
sm1.addNewValue(2, coord2);
sm1.addNewValue(3, coord3);

int array12[3] = { 10, 10, 10 };
SparseMatrix sm2(3, 1, "druga", array12);

int coord6[3] = { 1, 6, 9 };
sm2.addNewValue(4, coord6);

sm2.copyArray(sm1);
int coord7[3] = { 7, 8, 9 };
sm1.addNewValue(30, coord2);
sm2.addNewValue(80, coord7);
*/

/*
if (s == ADDMAT) {

int dym;
int *rngVal;
int *correct;
int defVal;

cin >> dym;

rngVal = new int[dym];
correct = new int[dym + 1];

bool check = true;

for (int i = 0; i < dym + 1 && check; i++) {
if (cin.get() == '\n' || !isdigit(cin.get())) {
cout << INCCOMM << endl;
check = false;
}
else {
int read;
cin >> read;
correct[i] = read;
}

}
for (int i = 0; i < dym; i++) {
rngVal[i] = correct[i];
cout << rngVal[i];
}
defVal = correct[dym];
//addmat 3 2 2 2 1
//addmat 3 2 1 4 0
// def 0 1 1 1 5
//int val;
//cin >> val;
//rngVal[i] = val;
//addmat 3 2 2 2 1
// def 0 1 1 1 5
// def 0 0 1 1 2
// def 0 1 0 1 1
// def 0 1 0 1 50

cin >> defVal;

string  n;
//cin >> n;

if (cin.get() == '\n') { // || isspace(cin.get()) ) {

//SparseMatrix(int dym, int rngVal[], int defVal, string nam)
matrixArr.push_back(new SparseMatrix(dym, rngVal, defVal));
}
else {
cin >> n;
matrixArr.push_back(new SparseMatrix(dym, rngVal, defVal, n));
}
delete[] correct;
delete[] rngVal;
}
*/

