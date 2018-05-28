#pragma once
#include "Tree.h"

#include <iostream>
#include <vector>
#include <string>
#include <cctype>
#include <sstream>
#include <istream>
#include <stdio.h>     
#include <math.h>    
#include <random>
#include <fstream>
#include <math.h> 
#include <algorithm>

using namespace std;





#define PLUS "+"
#define MINUS "-"
#define MULTI "*"
#define SUB "/"
#define SIN "sin"
#define COS "cos"
#define SUPERSUM "++"

//zmienne
#define X "x"
#define Y "y"
#define Z "Z"


#define MAX_NUM_OPER_IN_TREE 3	//maksymalna iloœæ operatorów w losowym drzewie
#define MAX_NUM_VAR_IN_TREE 2	//maksymalna iloœæ zmiennych w losowym drzewie


#define MIN_NUM_NODES_IN_TREE 2		//minimalna iloœæ generowanych wêz³ów w losowym drzewie
#define MAX_NUM_NODES_IN_TREE 5		//maksymalna iloœæ generowanych wêz³ów w losowym drzewie (bez naprawionych)

#define MIN_RAND_OPER 1			//minimalna wylosowana liczba do losowania operatora	
#define MAX_RAND_OPER 6			//maksymalna wylosowana liczba do losowania operatora	
#define MAX_RAND_OPER_WITHOUT_TRIG 4	//maksymalna wylosowana liczba do losowania operatora bez sin i cos

#define MIN_TYPE_NODE 1			//najmniejszy identyfikator typów
#define MAX_TYPE_NODE 3			//najwiêkszy identyfikator typów

#define MIN_RAND_VALUE 1			//najmniejsza wylosowana wartoœæ dla wêz³a
#define MAX_RAND_VALUE 30		//najwiêksza wylosowana wartoœæ dla wêz³a



#define NUM_OF_POPULATION 10				//rozmiar populacji
#define NUM_OF_ITERATION 5				//liczba iteracji
#define PROB_OF_CROSSING 50			//prwadopodobieñstwo krzy¿owania
#define PROB_OF_MUTATION 10			//prawdopodobieñstwo mutacji

#define ZERO 0
#define HUNDRED 100




class GeneticProgramming{
private:
	vector < Tree* > oldGeneration;
	vector < Tree* > newGeneration;



public:
	//-------KOSTRUKTORY I DESTRUKTORY-------------

	GeneticProgramming();


	~GeneticProgramming();



	//------------------------------FUNKCJE G£ÓWNE-------------------------------------

	void vRun();	//wykonuje ca³e zadanie

	void initialozation();	//inicjuje drzewa

	bool evaluation();		//wywo³uje ewaluacje dla drzew

	void selection();		//wykonuje selekcje

	void crossing();		//wykonuje krzy¿owanie

	void mutation();		//mutuje drzewa

	int findBest();			//zwraca indeks najlepszego drzewa

	void writeResults();	//zapisuje wyniki do pliku

	


	//--------FUNKCJE POMOCNICZE---------



	Tree* MakeRandomTree();					//tworzy losowe drzewo

	double function(double x, double y);	//obliczanie funkcji docelowej

	string* randomData(int numOfNodes);		//tworzy losow¹ tablicê z danymi do stworzenia drzewa

	string randomOperator(bool withSinCos);  //zwraca losowy operator z lub bez sin i cos

	string randomTypeOfNode(int &operCount, int &varCount); //zwraca losowy typ kolejnego wêz³a jakmi ma byæ dodany do losowego drzewa

	string randomVariable(int counter);		//zwraca zmienn¹ (ograniczona iloœæ

	int GetRandomNumber(int min, int max);	//zwraca losow¹ liczbê w przedziale (min, max)

	
	


	void split(const string &s, char delim, vector<string> &elems);

	vector<string> split(const string &s, char delim);
};

