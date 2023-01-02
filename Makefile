# vim: set noet :

.PHONY: clean all test release

MAVEN = mvn
release_path = ./release
api_module = surge-dashboard

clean:
	$(MAVEN) -v
	$(MAVEN) clean
	@rm -rf release
	@rm -rf target

all: clean
	$(MAVEN) -v
	$(MAVEN) package -U -Dmaven.test.skip=true

test: clean
	$(MAVEN) -v
	$(MAVEN) test

release:
	@rm -rf release
	@mkdir -p $(release_path)
	cp -f target/${api_module}.jar ${release_path}/${api_module}.jar
	cp -f start.sh $(release_path)
