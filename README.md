cookie-manager
==============
CookieManagerは、Cookie生成を行うために使用するAPIです。

初期設定を行って構築したCookieManagerのインスタンスを利用することで、
Cookie生成時にありがちなパスやドメインの設定忘れ/間違いを軽減することが可能となります。
また、HttpOnly属性をサポートしていない環境でも、HttpOnlyの属性を出力することが可能です。

インストール
------
mavenから取得できます。pomファイルのdependenciesに次の用に追加してください。

    <dependency>
        <groupId>net.jp.saf.http.cookie</groupId>
        <artifactId>cookie-manager</artifactId>
        <version>0.1.0</version>
    </dependency>

初期設定
------
使用する環境に合わせて、net.jp.saf.http.cookie.impl.CookieManagerImplクラスを構築して下さい。

[Seasar2](http://s2container.seasar.org/2.4/ja/index.html "Seasar2")の場合、次のようにdiconファイルを作成して下さい。

    <component name="cookieManager" class="net.jp.saf.http.cookie.impl.CookieManagerImpl">
        <property name="domain">"example.com"</property>
        <property name="path">"/foo/"</property>
        <property name="maxAge">-1</property>
        <property name="secure">false</property>
        <property name="httpOnly">false</property>
    </component>


使い方
------
net.jp.saf.http.cookie.CookieManagerインタフェースを経由して、Cookie操作を行います。

Seasar2の例です。

    CookieManager cookieManager = SingletonS2Container.getComponent("cookieManager");
    HttpServletResponse response = ResponseUtil.getResponse();
    // Cookieの出力
    cookieManager.cookie("sample1").value("baa").write(response);
    →sample1というCookieが出力されます。value以外の省略したパラメータは、初期設定した値が自動で設定されます。
    レスポンスヘッダーには、次のように出力されます。
    「Set-Cookie: sample1=baa; Domain=example.com; Path=/foo/」


    cookieManager.cookie("sample2").value("boo").secure().httpOnly().write(response);
    →別途設定した値は、設定値を上書きします。
    「Set-Cookie: sample2=boo; Domain=example.com; Path=/foo/; Secure; HttpOnly」


    //文字列での取得
    String sample3 = cookieManager.cookie("sample3").value("bee").maxAge(3600).httpOnly().toHeaderString();
    →「sample3=bee; Expires=Sun, 03-Feb-2013 10:54:08 GMT; Path=/foo/; Domain=example.com; HttpOnly」が取得できます。


HttpOnlyのサポート
------
Tomcat6のように、ServletAPIのバージョンによってHttpOnlyの属性が使えないコンテナでもHttpOnlyを出力します。
これは、実行環境のjavax.servlet.http.CookieがHttpOnlyのsetter/getterを持っていない場合、
Cookie出力値を文字列で取得し、HttpServletResponseにaddHeaderすることで実現しています。
