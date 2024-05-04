
run:
	clear


	javac --release 7 -cp .:lib/* Test/csvtoexcel.java Test/consultas.java Test/consultas2.java Test/utils.java Test/interfaz.java 
	#java Test/interfaz
	jar -cfvm sigecof.jar MANIFEST.MF Test/*.class

	cat stub.sh sigecof.jar > sigecof.run && chmod +x sigecof.run
	chmod 777 sigecof.jar
	chmod 777 sigecof.run
	./sigecof.run


