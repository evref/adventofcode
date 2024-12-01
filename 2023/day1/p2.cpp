#include <iostream>
#include <fstream>
#include <list>
#include <regex>
using namespace std;


void p2_extract_value(int* value, string line) {
    list<int> digit_list;
    
    regex pattern("[0-9]|(?=(one|two|three|four|five|six|seven|eight|nine)).{2}");

    regex_iterator<string::iterator> regex_it(line.begin(), line.end(), pattern);
    regex_iterator<string::iterator> regex_end;

    while (regex_it != regex_end) {
        string symbol = regex_it->str();
        ++regex_it;

        int digit;
        if (symbol.length() == 1) digit = symbol[0]-'0';
        else if (symbol == "on") digit = 1;
        else if (symbol == "tw") digit = 2;
        else if (symbol == "th") digit = 3;
        else if (symbol == "fo") digit = 4;
        else if (symbol == "fi") digit = 5;
        else if (symbol == "si") digit = 6;
        else if (symbol == "se") digit = 7;
        else if (symbol == "ei") digit = 8;
        else if (symbol == "ni") digit = 9;
        digit_list.push_back(digit);
    }

    switch (digit_list.size()) {
        case 0: 
            {
                string error_msg = "ERROR: Line contains no digits.";
                throw invalid_argument(error_msg);
                break;
            } 
        case 1: 
            {
                int first_num = digit_list.front();
                *value = first_num * 10 + first_num;
                break;
            }
        default:
            *value = digit_list.front() * 10 + digit_list.back();
    }
}
