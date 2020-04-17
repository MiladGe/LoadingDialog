LoadingDialog
==
Demo
--
![](https://github.com/gittjy/LoadingDialog/raw/master/demogif/自定义加载Dialog.gif)  



how to use
--
1- add jitpack repository to build.gralde(project level)

```
allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	     }
```
2- add library link to build.gradle(app level)

```
dependencies {
	        implementation 'com.github.MiladGe:LoadingDialog:v1.0.5'
	     }
```
3- Create instance using builder and use like below

```
LoadingDailog.Builder loadBuilder=new LoadingDailog.Builder(this)
                .setMessage("加载中...")
                .setCancelable(true)
                .setCancelOutside(true);
        LoadingDailog dialog=loadBuilder.create();
        dialog.show();
```

