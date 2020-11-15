echo "Iniciando pruebas"
cd /tmp && ls
echo "Obteniendo dependencias"
mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.0.2:go-offline
echo "Ejecuntando pruebas unitarias"
mvn test
echo "Ejecuntando Jacoco report"
mvn jacoco:report
echo "Ejecutando testing calidad sonarqube"
# http://sonarqube:9000 (Docker for windows, Docker for MAC o Docker Linux)
# http://192.168.99.101:9000 (Docker for toolbox) Usa la IP!!!
mvn sonar:sonar \
  -Dsonar.projectKey=hexneopetcare \
  -Dsonar.host.url=http://192.168.99.100:9000 \
  -Dsonar.login=5b4aa1d1a005af0ae617268868124eb9bbaee73a