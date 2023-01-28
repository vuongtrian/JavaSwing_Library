JAVAC = javac
JAVA = java
JAVAC_FLAGS = -g -d Library/bin -cp Library/bin
JAVA_FLAGS = -cp Library/bin
MAIN_BUSINESS_CLASS = business.Main
MAIN_DATA_CLASS = dataaccess.TestData
MAIN_VIEW_CLASS = librarysystem.Main

all: clean compile

compile:
	$(JAVAC) $(JAVAC_FLAGS) -sourcepath src Library/src/business/*.java Library/src/dataaccess/*.java Library/src/librarysystem/*.java Library/src/librarysystem/libpanel/*.java Library/src/librarysystem/adminpanel/*.java 

runb:
	$(JAVA) $(JAVA_FLAGS) $(MAIN_BUSINESS_CLASS)

rund:
	$(JAVA) $(JAVA_FLAGS) $(MAIN_DATA_CLASS)

runv:
	$(JAVA) $(JAVA_FLAGS) $(MAIN_VIEW_CLASS)

clean:
	rm -rf Library/bin/*
	rm -rf Library/bin
