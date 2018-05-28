/*

#include "Tree.h"

using namespace std;

#define ENTER "enter"
#define VARS "vars"
#define PRINT "print"
#define COMP "comp"
#define JOIN "join"
#define UNCCOMM "bad command"
#define EXIT "exit"

#define MAX_TASK_LENGTH 5
#define ASCII_SPACE 32



void printHelp() {
	cout << "enter <formula> - making new tree witch this mathematical formula\n";
	cout << "vars - printing all variables in the tree\n";
	cout << "print - printing tree in pre-order\n";
	cout << "comp <var0> <var1> ... <varN> - calculate tree with this values\n";
	cout << "join <formula> - making new tree, and merge to existing tree\n";
	cout << "exit - exit the program\n";
}


void split(const string &s, char delim, vector<string> &elems) {
	stringstream ss(s);
	string item;
	while (getline(ss, item, delim)) {
		elems.push_back(item);
	}
}

vector<string> split(const string &s, char delim) {
	vector<string> elems;
	split(s, delim, elems);
	return elems;
}

int main() {

	string *arr = new string[3];
	arr[0] = "+";
	arr[1] = "4";
	arr[2] = "5";


	Tree drzewo, drzewo2, drzewo3;
	bool isRepaired = drzewo.makeTree(arr, 3);

	//if (isRepaired) cout << "naprawiono" << endl;

	drzewo.print();
	cout << endl;

	string *arr2 = new string[3];
	arr2[0] = "*";
	arr2[1] = "80";
	arr2[2] = "55";

	//bool isRepaired2 = drzewo2.makeTree(arr2, 3);
	//if (isRepaired2) cout << "naprawiono" << endl;
	//drzewo2.print();
	//cout << "wydrukowano drzewo2" << endl;

	drzewo.mergeTree(arr2, 3);

	//drzewo3 = drzewo + drzewo2;
	drzewo.print();


	system("pause");
	return 0;
}

/*
while (1) {

cout << ">>>";

string command;
cin >> command;
}
*/

/*

int stringIter = 0;
string task = "";
bool stop = false;

while (stringIter < strGuide.length() && ((int)strGuide.at(stringIter) != ASCII_SPACE) && !stop) {
task += strGuide.at(stringIter);
stringIter++;
if (stringIter > MAX_TASK_LENGTH) stop = true;//najdluzsze polecenie ma 5 znakow
}

*/


/*
string *arr = new string[10];
arr[0] = "+";
arr[1] = "*";
arr[2] = "5";
arr[3] = "sin";
arr[4] = "x";
arr[5] = "*";
arr[6] = "+";
arr[7] = "a";
arr[8] = "b";
arr[9] = "8";

Tree drzewo;
bool isRepaired = drzewo.makeTree(arr, 10);


if (isRepaired) cout << "naprawiono" << endl;

drzewo.print();
cout << endl;
drzewo.getVar();
cout << "\n\n\n";

string *calc = new string[3];
calc[0] = "15";
calc[1] = "18";
calc[2] = "23";
cout << drzewo.calculate(calc, 3) << endl;
*/

/*
int stringIter = 0;
string task = "";
bool stop = false;

while (stringIter < strGuide.length() && ((int)strGuide.at(stringIter) != ASCII_SPACE) && !stop) {
task += strGuide.at(stringIter);
stringIter++;
if (stringIter > MAX_TASK_LENGTH) stop = true;//najdluzsze polecenie ma 5 znakow
}
*/

/*
string *arr = new string[3];
arr[0] = "+";
arr[1] = "4";
arr[2] = "5";


Tree drzewo, drzewo2, drzewo3;
bool isRepaired = drzewo.makeTree(arr, 3);

//if (isRepaired) cout << "naprawiono" << endl;

drzewo.print();
cout << endl;

string *arr2 = new string[3];
arr2[0] = "*";
arr2[1] = "80";
arr2[2] = "55";

//bool isRepaired2 = drzewo2.makeTree(arr2, 3);
//if (isRepaired2) cout << "naprawiono" << endl;
//drzewo2.print();
//cout << "wydrukowano drzewo2" << endl;

drzewo3 = *(drzewo.addTree(arr2, 3));

//drzewo3 = drzewo + drzewo2;
drzewo3.print();

*/