PackageLoader fileInPackage: 'Android'.
| response android result |
android := Android new.

android dialogCreateAlert: #('I like swords.' 'Do you like swords?').
android dialogSetPositiveButtonText: 'Yes'.
android dialogSetNegativeButtonText: 'No'.
android dialogShow.
response := android dialogGetResponse.
response inspect.
android dialogDismiss.
result := (response at: 'result') at: 'which'.
result = 'positive'
	ifTrue: [ android dialogCreateAlert:'Yay! I like swords too!']
	ifFalse: [ android dialogCreateAlert:'Oh. How sad.'].

android dialogSetNeutralButtonText: 'Ok'; dialogShow; dialogGetResponse; dialogDismiss.

android close.


