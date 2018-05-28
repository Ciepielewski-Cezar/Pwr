#include <iostream>
#include <string>
#include <vector>
#include "Tree.h"

using namespace std;

#define MAX_TASK_LENGTH 5

#define ENTER "enter"
#define ENTER_LENGTH 5
#define SHOW_VARIABLES "vars"
#define SHOW_TREE "print"
#define COMPUTE_FORMULA "comp"
#define COMP_LENGTH 4
#define JOIN_FORMULA "join"
#define JOIN_LENGTH 4

class TestInterface
{
private:
	string strGuide;
	Tree* tree;

public:
	TestInterface() {
		strGuide = "";
		tree = new Tree();
	}

	~TestInterface() {
		delete tree;
	}

	string trim(const string& str, const string& whitespace = " \t") {
		const auto strBegin = str.find_first_not_of(whitespace);
		if (strBegin == std::string::npos)
			return ""; // no content

		const auto strEnd = str.find_last_not_of(whitespace);
		const auto strRange = strEnd - strBegin + 1;

		return str.substr(strBegin, strRange);
	}
	string reduce(const string& str, const string& fill = " ", const string& whitespace = " \t") {
		// trim first
		auto result = trim(str, whitespace);

		// replace sub ranges
		auto beginSpace = result.find_first_of(whitespace);
		while (beginSpace != std::string::npos)
		{
			const auto endSpace = result.find_first_not_of(whitespace, beginSpace);
			const auto range = endSpace - beginSpace;

			result.replace(beginSpace, range, fill);

			const auto newStart = beginSpace + fill.length();
			beginSpace = result.find_first_of(whitespace, newStart);
		}

		return result;
	}
	static string getNextTask(int &stringIter, string strGuide);
	string getNextTask(int &stringIter);
	static bool isStringAPositiveNumber(string task);
	static bool isStringANumber(string task);
	int enter(string formula);
	int print();
	//int vars(container &variables);
	int comp(string vars);
	/*
	int join(string formula); */
	void run();
};