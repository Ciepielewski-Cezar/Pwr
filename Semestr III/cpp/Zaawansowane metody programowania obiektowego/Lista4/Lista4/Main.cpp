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
	printHelp();
	Tree *tree = new Tree();

	while (1) {
		cout << ">>>";

		vector<string> words;
		string command;
		cin >> command;

		string read;
		getline(cin, read);
		if (read[0] == ASCII_SPACE) read.erase(0, 1);

		words = split(read, ' ');
		int arrLength = words.size();
		string *array = new string[arrLength];

		for (int i = 0; i < words.size(); i++) {
			array[i] = words.at(i);
		}

		if (command == ENTER) {
			bool isRepair = tree->makeTree(array, arrLength);
			if (isRepair) cout << "tree was repaired" << endl;
		}
		else if (command == VARS) {
			tree->getVar();
			cout << endl;
		}
		else if (command == PRINT) {
			cout << tree->print() << endl;
		}
		else if (command == COMP) {
			int err = 0;
			double result = tree->calculate(array, arrLength, err);

			if (err == 1) cout << "wrong input size" << endl;
			else  cout << "wynik to " << result << endl;
		}
		else if (command == JOIN) {
			tree->mergeTree(array, arrLength);
			//tree->printNumOfNodes();
		}
		else if (command == EXIT) {
			words.clear();
			delete tree;
			delete[] array;
			exit(0);
		}
		else
			cout << UNCCOMM << endl;

		words.clear();
		delete[] array;

	}



	system("pause");
	return 0;
}

*/