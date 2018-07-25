grammar Set;

compilationUnit : set EOF ;

set
    : '{' '}' # emptySet
    | '{' elem (',' elem)* '}' # nonEmptySet
    ;

elem
    : simpleElement
    | set
    ;

simpleElement : ELEM;

ELEM
    : ('A'..'Z' | 'a'..'z' | '0'..'9' |  '_')+
    ;

WS : [ \t\r\n]+ -> skip ;