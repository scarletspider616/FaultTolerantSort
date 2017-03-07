Welcome to my fault tolerant sorting algorithm

Documentation (UML and use case seq. diagrams) are available in doc/

This algorithm uses RcB to sort a randomized list of integers (data generator
is also provided). The primary variant algorithm is heap sort in java, the 
secondary (backup) algorithm is insertion sort in C. 

To install (linux & mac only): 
	chmod +x install.sh
	chmod +x run.sh
	./install.sh

To run: 
	./run.sh

In some cases, pseudocode or example (mostly for file I/O) were used 
as reference. In this instances proper acknowledgments are noted in 
the code commentary. There is not necessarily need for licensing as the 
the concepts are considered common knowledge, however for completeness the 
license information is noted here and applicable licenses are included 
in the licensing dir, as well as this software's own license (Apache 2.0).

Wikipedia pseudo-code: CC-BY-SA 3.0
Stackoverflow (pre Feb 2016): CC-BY-SA 3.0
Stackoverflow (post Feb 2016): MIT (not requried to include licensing doc
for more information: https://opensource.org/licenses/MIT, 
http://meta.stackexchange.com/questions/271080/the-mit-license-clarity-on-using-code-on-stack-overflow-and-stack-exchange)
Codecodex: GFDL 
ECE 422 Example code by Dr. Dick is also used in this project. 