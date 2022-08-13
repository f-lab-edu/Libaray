### Code
- [Google Java Style Guide Docs](https://google.github.io/styleguide/javaguide.html)
- [Google style guide for Intellij](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml)
### Branch
```
main
develop
feature/{issue #}/{module}
```

### Commit
```yaml
feat: [구현 내용] #이슈번호
chore: [빌드 내용 수정, 패키지 디펜던시 등 버전 관리 내용] #이슈번호
docs: [문서 수정] #이슈번호
refactor: [코드 리팩토링] #이슈번호
test: [테스트 코드 추가, 수정] #이슈번호
bugfix: [버그 수정] #이슈번호
```

### Project Package Structure
```yaml
common:
  log: # log4j2, async 설정 잊지않기..
  exception:
  validation:
    - validator
    - validationPolicy interface
    - validation policy implementation
  util: #( ex. parser, static functions )
config:
    - security
infrastructure: # ( ex. MessageSender )
domainName1: # ( ex. Order )
  controller:
  service:
    - facade
    - query # 파일을 나누고 싶어질때 나눌 수 있다
    - command
  repository:
  dto:
  business exception:
  domain: #( ex Campaign, .... 등 묶일 수 있는 Entity, VO 객체)
    - entity
domainName2:
  controller:
  service:
  repository:
  dto:
  business exception:
  domain:
```

