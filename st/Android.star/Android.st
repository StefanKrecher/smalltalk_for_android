PackageLoader fileInPackage: 'TCP'.
DLD addLibrary: 'libc'.

Object subclass: Android [
| ap_port ap_host ap_handshake id socket |

	Android class >> new [
		| a id|
		a:= super new.
		a init.
		^a
	]

	init [
		| p json |
		ap_port := ((Smalltalk getenv: 'AP_PORT') asInteger).
		p := (Smalltalk getenv: 'AP_PORT').
		ap_host := Smalltalk getenv: 'AP_HOST'.
		ap_handshake := Smalltalk getenv: 'AP_HANDSHAKE'.
		id := 1.
		socket := TCP.Socket remote: ap_host port: ap_port.
		self rpc: '_authenticate' with: ap_handshake.
	]

	rpc: method with: argument [
		| json dict resp |
		dict := Dictionary new.
		dict at: 'id' put: id.
		id := id + 1.
		dict at: 'method' put: method.
		(argument class = Array)
			ifTrue: [ dict at: 'params' put: argument ]
			ifFalse: [ dict at: 'params' put: (Array with: argument) ].
	
		socket nextPutAll: (ReadStream on: (JSONReader toJSON: dict)).
		socket nextPut: (Character value: 10).
		socket flush.

		resp :=  ReadWriteStream on: String new.

		[ socket peek == $<10> ] whileFalse: [ resp nextPut: socket next ].
		socket next.
		^JSONReader fromJSON: (resp contents)
	]
	
	doesNotUnderstand: aMessage [
		| args |
		^self rpc: (aMessage selector asString copyWithout: $:) with: (
		[ aMessage arguments isEmpty ifTrue: [ #() ] ifFalse: [ aMessage argument ]] value).
	]

	
	close [
		socket close.
	]
]

