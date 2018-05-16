/*
 * 琛ㄥ崟楠岃瘉鎻掍欢 easyform
 * http://thesmallcar.github.io/jQuery.easyform/
 * Author : 鏉庡叞闈�
 * 鏈夐棶棰樻杩庡姞鍏Q缇わ紝222578556锛圚ello PHP锛夛紝鎴戞槸缇や富锛氬ぇ鏍戙€�
 * 2014-11-5
 * 鐢ㄤ簬琛ㄥ崟楠岃瘉
 * 鍙鍦ㄩ渶瑕侀獙璇佺殑鎺т欢涓婃坊鍔爀asyform灞炴€у嵆鍙紝澶氫釜灞炴€х敤[;]杩炴帴锛岃娉曠被浼糲ss
 * 灞炴€у垪琛細
 *      null
 *      email
 *      char-normal         鑻辨枃銆佹暟瀛椼€佷笅鍒掔嚎
 *      char-chinese        涓枃銆佽嫳鏂囥€佹暟瀛椼€佷笅鍒掔嚎銆佷腑鏂囨爣鐐圭鍙�
 *      char-english        鑻辨枃銆佹暟瀛椼€佷笅鍒掔嚎銆佽嫳鏂囨爣鐐圭鍙�
 *      length:1 10 / length:4      鑳藉璇嗗埆姹夊瓧绛夊瀛楃闀垮害
 *      equal:xxx                               绛変簬鏌愪釜瀵硅薄鐨勫€硷紝鍐掑彿鍚庢槸jq閫夋嫨鍣ㄨ娉�
 *      ajax:fun()
 *      real-time                               瀹炴椂妫€鏌�
 *      date                    2014-10-31
 *      time                    10:30:00
 *      datetime            2014-10-31 10:30:00
 *      money               姝ｆ暟锛屼袱浣嶅皬鏁�
 *      uint :1 100                 姝ｆ暣鏁� , 鍙傛暟涓鸿捣濮嬪€煎拰鏈€澶у€�
 *      number              涓嶉檺闀垮害鐨勬暟瀛楀瓧绗︿覆
 *      float:7 2
 *      regex:"^(\\d{4})-(\\d{2})-(\\d{2})$"
 *      mobile              鎵嬫満
 * */

/*
 * 鏇存柊鏃ュ織
 * Author: 閮戝ぇ鏌�(galandeo)
 * 2015/12/6
 *
 * --淇 BUG
 *    璇UG瀵艰嚧鍥犲厓绱犳湭璁剧疆ID鍊艰€屽紩鍙戜笉鑳藉脊鍑烘彁绀虹殑BUG
 * --澧炲姞 mobile楠岃瘉
 *    澧炲姞mobile瑙勫垯楠岃瘉
 *
 * */

/**
 * 璇诲彇涓€涓帶浠剁殑鎸囧畾data灞炴€э紝骞堕€氳繃锛氬拰锛涙潵鍒嗗壊鎴恔ey/value鍊煎
 * @id string 鎺т欢id
 * @name string 灞炴€у悕绉�
 **/
if (typeof(easy_load_options) == "undefined")
{
    // function easy_load_options(id, name) // # by galandeo, fix bug
    function easy_load_options(obj, name)
    {
        var options = $(obj).data(name);
        // var options = $("#" + id).data(name);

        //灏嗗瓧绗︿覆鐢紱鍒嗗壊
        options = (!!options ? options.split(";") : undefined);

        var data = Object();

        if (!!options)
        {
            var index;
            for (index in options)
            {
                var temps = options[index];
                var p = temps.indexOf(":");

                var temp = [];
                if (-1 == p)
                {
                    temp[0] = temps;
                    temp[1] = "";
                }
                else
                {
                    temp[0] = temps.substring(0, p);
                    temp[1] = temps.substring(p + 1);
                }

                if (temp[0].length > 0)
                {
                    data[temp[0]] = temp[1];
                }
            }
        }

        return data;
    }
}

//easyform
(function ($, window, document, undefined)
{
    /*
     鏋勯€犲嚱鏁�
     **/
    var _easyform = function (ele, opt)
    {
        this.form = ele;

        if (0 == this.form.length && "form" != this.form[0].localName)
        {
            throw new Error("easyform need a form !");
        }

        this.defaults = {
            easytip: true,   //鏄惁鏄剧ずeasytip锛屽彲浠ュ叧闂悗锛屼娇鐢ㄨ嚜瀹氫箟鐨勬彁绀轰俊鎭�
            success: null,
            error: null,
            complete: null,
            per_validation: null
        };

        this.options = $.extend({}, this.defaults, opt);

        //this.result = [];
        this.inputs = [];

        this.counter_success = 0;   //宸茬粡鍒ゆ柇鎴愬姛鐨刬nput璁℃暟
        this.counter = 0;                   //宸茬粡鍒ゆ柇鐨刬nput璁℃暟
        this.is_submit = true;  //鏄惁鎻愪氦锛屽鏋滀负false锛屽嵆浣块獙璇佹垚鍔熶篃涓嶄細鎵ц鎻愪氦

        //浜嬩欢瀹氫箟
        this.success = this.options.success;
        this.error = this.options.success;
        this.complete = this.options.complete;
        this.per_validation = this.options.per_validation;     //鍦ㄦ墍鏈夐獙璇佷箣鍓嶆墽琛�
    };

    //鏂规硶
    _easyform.prototype = {

        init: function ()
        {
            var $this = this;
            $this._load();

            //鏀瑰啓 submit 鐨勫睘鎬э紝渚夸簬鎺у埗
            this.submit_button = this.form.find("input:submit");
            this.submit_button.each(function ()
            {
                var button = $(this);
                button.attr("type", "button");

                //鎻愪氦鍓嶅垽鏂�
                button.click(function ()
                {
                    $this.submit(true);
                });
            });

            return this;
        },

        _load: function (iterator)
        {
            if (!iterator)
            {
                iterator = "input:visible, textarea:visible";
            }

            //鏋愭瀯鏃х殑easyinput锛岄槻姝eal-time鏉′欢涓嬬殑閲嶅楠岃瘉銆�
            for (var i in this.inputs)
            {
                this.inputs[i].destructor();
            }

            this.inputs.splice(0, this.inputs.length);

            var $this = this;

            this.form.find(iterator).each(function (index, input)
            {
                //鎺掗櫎 hidden銆乥utton銆乻ubmit銆乫ile
                if (input.type != "hidden" && input.type != "button" && input.type != "submit"
                    && input.type != "file")
                {
                    if (input.type == "radio" || input.type == "checkbox")
                    {
                        var name = input.name;

                        for (index in  $this.inputs)
                        {
                            if (name == $this.inputs[index].input[0].name)
                            {
                                return;
                            }
                        }
                    }

                    var checker = $(input).easyinput({easytip: $this.options.easytip});

                    checker.error = function (e, r)
                    {
                        $this.is_submit = false;
                        //$this.result.push(e);

                        if (!!$this.error)    //澶辫触浜嬩欢
                        {
                            $this.error($this, e, r);
                        }
                    };

                    checker.complete = function (e)
                    {
                        $this.counter++;    //璁℃暟

                        if ($this.counter == $this.inputs.length)
                        {
                            if (!!$this.complete)    //缁撴潫浜嬩欢
                            {
                                $this.complete($this);
                            }
                        }
                    };

                    checker.success = function (e)
                    {
                        $this.counter_success++;    //鎴愬姛璁℃暟

                        if ($this.counter_success == $this.inputs.length)
                        {
                            $this.counter_success = 0;
                            $this.counter = 0;

                            if (!!$this.success)    //鎴愬姛浜嬩欢
                            {
                                $this.success($this);
                            }

                            if (!!$this.is_submit)
                            {
                                $this.form.submit();
                            }
                        }
                    };

                    $this.inputs.push(checker);
                }
            });
        },

        _check: function (submit)
        {
            this.counter_success = 0;
            this.counter = 0;
            this.is_submit = submit;

            //鎵цper_validation浜嬩欢
            if (!!this.per_validation)
            {
                this.is_submit = this.per_validation(this);
            }

            //濡傛灉娌℃湁闇€瑕佸垽鏂殑鎺т欢
            if (this.inputs.length == 0)
            {
                if (!!this.success)    //鎴愬姛浜嬩欢
                {
                    this.success(this);
                }

                if (!!this.complete)    //缁撴潫浜嬩欢
                {
                    this.complete(this);
                }

                if (this.is_submit)
                {
                    this.form.submit();
                }
            }

            var index;
            for (index in this.inputs)
            {
                this.inputs[index].validation();
            }
        },

        /*
         * 琛ㄥ崟鎻愪氦鍑芥暟
         * @submit锛歜ool鍊硷紝鐢ㄤ簬瀹氫箟鏄惁鐪熺殑鎻愪氦琛ㄥ崟
         * */
        submit: function (submit)
        {
            this._load();           //閲嶆柊杞藉叆鎺т欢

            this._check(submit);        //楠岃瘉骞舵彁浜�
        },

        check: function (iterator)
        {
            this._load(iterator);       //閲嶆柊杞藉叆鎺т欢

            this._check(false);        //楠岃瘉涓嶆彁浜�

            return this.counter == this.counter_success;
        }
    };

    //娣诲姞鍒癹query
    $.fn.easyform = function (options)
    {
        var validation = new _easyform(this, options);

        return validation.init();
    };

})(jQuery, window, document);

//easyinput
(function ($, window, document, undefined)
{
    //鍗曚釜input鐨勬鏌ュ櫒鏋勯€犲嚱鏁�
    var _easyinput = function (input, opt)
    {
        if (0 == input.length)
        {
            throw new Error("easyform need a input object !");
        }

        this.input = input;     //缁戝畾鐨勬帶浠�
        this.rules = [];            //瑙勫垯

        //浜嬩欢
        this.error = null;
        this.success = null;
        this.complete = null;


        this.defaults = {
            "easytip": true,   //鏄惁鏄剧ずeasytip
            "real-time": false
        };

        this.tip = null;    //鍏宠仈鐨則ip

        //璇诲彇 data-easyform灞炴€�
        // this.rules = easy_load_options(input[0].id, "easyform"); // # by galandeo, fix bug
        this.rules = easy_load_options(input[0], "easyform");

        //澶勭悊data-easyform涓殑閰嶇疆灞炴€�
        var o = Object();
        for (var index in this.rules)
        {
            if (index == "easytip")
            {
                o["easytip"] = this.rules[index];
            }
            else if (index == "real-time")
            {
                o["real-time"] = true;
            }
        }

        delete this.rules["easytip"];
        delete this.rules["real-time"];

        this.options = $.extend({}, this.defaults, opt, o);

        this.counter_success = 0;   //璁℃暟鍣紝璁板綍宸茬粡鏈夊灏戜釜鏉′欢鎴愬姛
        this.counter = 0;                   //璁℃暟鍣紝璁板綍宸茬粡楠岃瘉浜嗗灏戞潯浠�

        this.is_error = false;      //閿欒鏍囧織
    };

    //鍗曚釜input鐨勬鏌ュ櫒
    _easyinput.prototype = {

        init: function ()
        {
            //鍒濆鍖杄asytip
            if (true === this.options.easytip)
            {
                this.tip = $(this.input).easytip();
            }

            var $this = this;

            //鏄惁瀹炴椂妫€鏌�
            if (!!this.rules && this.options["real-time"])
            {
                this.input.on("blur", function ()
                {
                    $this.validation();
                });
            }

            return this;
        },

        /**
         * 瑙勫垯鍒ゆ柇
         * */
        validation: function ()
        {
            this.value = this.input.val();
            this.counter_success = 0;   //璁℃暟鍣ㄦ竻闆�
            this.counter = 0;
            this.is_error = false;

            if (this.input.attr("type") == "radio" || this.input.attr("type") == "checkbox")
            {
                var name = this.input.attr("name");

                var v = $('input[name="' + name + '"]:checked').val();

                if (false == this._null(this, v, this.rules))
                {
                    if (false == this.is_error)
                    {
                        this._success();
                    }
                }
            }
            else if (false == this._null(this, this.value, this.rules))
            {
                delete this.rules.null;

                for (var index in this.rules)
                {
                    //璋冪敤鏉′欢鍑芥暟
                    if (!!this.judge[index])
                    {
                        this.judge[index](this, this.value, this.rules[index]);
                    }
                }

                //濡傛灉娌℃湁鍐欎换浣曡鍒�
                if (Object.keys(this.rules).length == 0)
                {
                    this._success();
                }
            }
        },

        _error: function (rule)
        {
            this.counter++;

            if (!!this.error)
            {
                this.error(this.input[0], rule);
            }

            if (!!this.complete && this.counter == Object.keys(this.rules).length)
            {
                this.complete(this.input[0]);
            }

            if (false == this.is_error)
            {
                var msg = $(this.input).data("message-" + rule);

                if (!msg)
                {
                    msg = $(this.input).data("message");
                }

                msg = !msg ? "鏍煎紡閿欒" : msg;

                if (true === this.options.easytip)
                {
                    this.tip.show(msg);
                }

                this.is_error = true;
            }

            return false;
        },

        _success: function ()
        {
            if (!!this.success)
            {
                this.success(this.input);
            }

            return true;
        },

        _success_rule: function (rule)
        {
            this.counter++;
            this.counter_success++;

            if (!!this.complete && this.counter == Object.keys(this.rules).length)
            {
                this.complete(this.input[0]);
            }

            if (this.counter_success == Object.keys(this.rules).length)
            {
                this._success();
            }

            return true;
        },

        _null: function (ei, v, r)
        {
            if (!v)
            {
                //rule涓嶄负绌哄苟涓斿惈鏈塶ull
                if (!!r && typeof(r["null"]) != "undefined")
                {
                    return ei._success();
                }
                else
                {
                    return ei._error("null");
                }
            }
            else
            {
                return false;
            }
        },

        /*
         * 鎸夌収鍚勭rule杩涜鍒ゆ柇鐨勫嚱鏁版暟缁�
         * 閫氳繃瀵筳udge娣诲姞鎴愬憳鍑芥暟锛屽彲浠ユ墿鍏呰鍒�
         * */
        judge: {
            "char-normal": function (ei, v, p)
            {
                if (false == /^\w+$/.test(v))
                {
                    return ei._error("char-normal");
                }
                else
                {
                    return ei._success_rule("char-normal");
                }
            },

            "char-chinese": function (ei, v, p)
            {
                if (false == /^([\w]|[\u4e00-\u9fa5]|[ 銆傦紝銆侊紵锟モ€溾€濃€樷€欙紒锛氥€愩€戙€娿€嬶紙锛夆€斺€�.,?!$'":+-])+$/.test(v))
                {
                    return ei._error("char-chinese");
                }
                else
                {
                    return ei._success_rule("char-chinese");
                }
            },

            "char-english": function (ei, v, p)
            {
                if (false == /^([\w]|[ .,?!$'":+-])+$/.test(v))
                {
                    return ei._error("char-english");
                }
                else
                {
                    return ei._success_rule("char-english");
                }
            },

            "email": function (ei, v, p)
            {
                if (false == /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test(v))
                {
                    return ei._error("email");
                }
                else
                {
                    return ei._success_rule("email");
                }
            },

            "mobile": function (ei, v, p)
            {
                if (false == /^(0|86|17951)?(1)(3[0-9]|4[0-9]|5[0-9]|7[0-9]|8[0-9])[0-9]{8}$/.test(v))
                {
                    return ei._error("mobile");
                }
                else
                {
                    return ei._success_rule("mobile");
                }
            },

            "length": function (ei, v, p)
            {
                var range = p.split(" ");

                //濡傛灉闀垮害璁剧疆涓� length:6 杩欐牱鐨勬牸寮�
                if (range.length == 1)
                {
                    range[1] = range[0];
                }

                var len = v.replace(/[^\x00-\xff]/g, "aa").length;

                if (len < range[0] || len > range[1])
                {
                    return ei._error("length");
                }
                else
                {
                    return ei._success_rule("length");
                }
            },

            "idcard": function (ei, v, p)
            {
                /*
                 * 韬唤璇�15浣嶇紪鐮佽鍒欙細dddddd yymmdd xx p
                 * dddddd锛�6浣嶅湴鍖虹紪鐮�
                 * yymmdd: 鍑虹敓骞�(涓や綅骞�)鏈堟棩锛屽锛�910215
                 * xx: 椤哄簭缂栫爜锛岀郴缁熶骇鐢燂紝鏃犳硶纭畾
                 * p: 鎬у埆锛屽鏁颁负鐢凤紝鍋舵暟涓哄コ
                 *
                 * 韬唤璇�18浣嶇紪鐮佽鍒欙細dddddd yyyymmdd xxx y
                 * dddddd锛�6浣嶅湴鍖虹紪鐮�
                 * yyyymmdd: 鍑虹敓骞�(鍥涗綅骞�)鏈堟棩锛屽锛�19910215
                 * xxx锛氶『搴忕紪鐮侊紝绯荤粺浜х敓锛屾棤娉曠‘瀹氾紝濂囨暟涓虹敺锛屽伓鏁颁负濂�
                 * y: 鏍￠獙鐮侊紝璇ヤ綅鏁板€煎彲閫氳繃鍓�17浣嶈绠楄幏寰�
                 *
                 * 鍓�17浣嶅彿鐮佸姞鏉冨洜瀛愪负 Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ]
                 * 楠岃瘉浣� Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]
                 * 濡傛灉楠岃瘉鐮佹伆濂芥槸10锛屼负浜嗕繚璇佽韩浠借瘉鏄崄鍏綅锛岄偅涔堢鍗佸叓浣嶅皢鐢╔鏉ヤ唬鏇�
                 * 鏍￠獙浣嶈绠楀叕寮忥細Y_P = mod( 鈭�(Ai脳Wi),11 )
                 * i涓鸿韩浠借瘉鍙风爜1...17 浣�; Y_P涓烘牎楠岀爜Y鎵€鍦ㄦ牎楠岀爜鏁扮粍浣嶇疆
                 */

                //15浣嶅拰18浣嶈韩浠借瘉鍙风爜鐨勬鍒欒〃杈惧紡
                var reg = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;

                //濡傛灉閫氳繃璇ラ獙璇侊紝璇存槑韬唤璇佹牸寮忔纭紝浣嗗噯纭€ц繕闇€璁＄畻
                if (reg.test(v))
                {
                    if (v.length == 18)
                    {
                        var idCardWi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2]; //灏嗗墠17浣嶅姞鏉冨洜瀛愪繚瀛樺湪鏁扮粍閲�
                        var idCardY = [1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2]; //杩欐槸闄や互11鍚庯紝鍙兘浜х敓鐨�11浣嶄綑鏁般€侀獙璇佺爜锛屼篃淇濆瓨鎴愭暟缁�
                        var idCardWiSum = 0; //鐢ㄦ潵淇濆瓨鍓�17浣嶅悇鑷箹浠ュ姞鏉冨洜瀛愬悗鐨勬€诲拰
                        for (var i = 0; i < 17; i++)
                        {
                            idCardWiSum += v.substring(i, i + 1) * idCardWi[i];
                        }

                        var idCardMod = idCardWiSum % 11;//璁＄畻鍑烘牎楠岀爜鎵€鍦ㄦ暟缁勭殑浣嶇疆
                        var idCardLast = v.substring(17);//寰楀埌鏈€鍚庝竴浣嶈韩浠借瘉鍙风爜

                        //濡傛灉绛変簬2锛屽垯璇存槑鏍￠獙鐮佹槸10锛岃韩浠借瘉鍙风爜鏈€鍚庝竴浣嶅簲璇ユ槸X
                        if (idCardMod == 2)
                        {
                            if (idCardLast == "X" || idCardLast == "x")
                            {
                                return ei._success_rule("idcard");
                            }
                            else
                            {
                                return ei._error("idcard");
                            }
                        }
                        else
                        {
                            //鐢ㄨ绠楀嚭鐨勯獙璇佺爜涓庢渶鍚庝竴浣嶈韩浠借瘉鍙风爜鍖归厤锛屽鏋滀竴鑷达紝璇存槑閫氳繃锛屽惁鍒欐槸鏃犳晥鐨勮韩浠借瘉鍙风爜
                            if (idCardLast == idCardY[idCardMod])
                            {
                                return ei._success_rule("idcard");
                            }
                            else
                            {
                                return ei._error("idcard");
                            }
                        }
                    }
                }
                else
                {
                    return ei._error("idcard");
                }
            },

            "equal": function (ei, v, p)
            {
                var pair = $(p);
                if (0 == pair.length || pair.val() != v)
                {
                    return ei._error("equal");
                }
                else
                {
                    return ei._success_rule("equal");
                }
            },

            "ajax": function (ei, v, p)
            {
                // 涓篴jax澶勭悊娉ㄥ唽鑷畾涔変簨浠�
                // HTML涓墽琛岀浉鍏崇殑AJAX鏃讹紝闇€瑕佸彂閫佷簨浠� easyform-ajax 鏉ラ€氱煡 easyinput
                // 璇ヤ簨浠跺彧鏈変竴涓猙ool鍙傛暟锛宔asyinput 浼氭牴鎹繖涓€煎垽鏂璦jax楠岃瘉鏄惁鎴愬姛
                ei.input.delegate("", "easyform-ajax", function (e, p)
                {
                    ei.input.unbind("easyform-ajax");

                    if (false == p)
                    {
                        return ei._error("ajax");
                    }
                    else
                    {
                        return ei._success_rule("ajax");
                    }
                });

                eval(p);
            },

            "date": function (ei, v, p)
            {
                if (false == /^(\d{4})-(\d{2})-(\d{2})$/.test(v))
                {
                    return ei._error("date");
                }
                else
                {
                    return ei._success_rule("date");
                }
            },

            "time": function (ei, v, p)
            {
                if (false == /^(\d{2}):(\d{2}):(\d{2})$/.test(v))
                {
                    return ei._error("time");
                }
                else
                {
                    return ei._success_rule(v);
                }
            },

            "datetime": function (ei, v, p)
            {
                if (false == /^(\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2}):(\d{2})$/.test(v))
                {
                    return ei._error("datetime");
                }
                else
                {
                    return ei._success_rule("datetime");
                }
            },

            "money": function (ei, v, p)
            {
                if (false == /^([1-9][\d]{0,10}|0)(\.[\d]{1,2})?$/.test(v))
                {
                    return ei._error("money");
                }
                else
                {
                    return ei._success_rule("money");
                }
            },

            "number": function (ei, v, p)
            {
                if (false == /^\d{1,}$/.test(v))
                {
                    return ei._error("number");
                }
                else
                {
                    return ei._success_rule("number");
                }
            },

            "float": function (ei, v, p)
            {
                var range = p.split(" ");

                //濡傛灉闀垮害璁剧疆涓� float:6 杩欐牱鐨勬牸寮�
                //蹇呴』瀹氫箟鏁存暟鍜屽皬鏁扮殑浣嶆暟
                if (range.length != 2)
                {
                    return ei._error("float");
                }
                else if (range[0] + range[1] > 16)
                {
                    console.warn("鎮ㄧ殑" + ei.input.id + "float瑙勫垯閰嶇疆鍙兘涓嶆纭�!璇蜂繚璇佹暣鏁颁綅鏁�+灏忔暟浣嶆暟 < 16");
                }

                var pattern = new RegExp("^([1-9][\\d]{0," + range[0] + "}|0)(\\.[\\d]{1," + range[1] + "})?$");

                if (false == pattern.test(v))
                {
                    return ei._error("float");
                }
                else
                {
                    return ei._success_rule("float");
                }
            },

            "uint": function (ei, v, p)
            {
                v = parseInt(v);

                var range = p.trim().split(" ");

                if ("" == p.trim())
                {
                    console.warn("鎮ㄧ殑" + ei.input.id + "uint瑙勫垯锛屾病鏈夎缃€煎煙!");
                    range[0] = 0;
                }

                if (range.length == 1)
                {
                    range[1] = 999999999999999;
                }

                range[0] = parseInt(range[0]);
                range[1] = parseInt(range[1]);

                if (isNaN(v) || isNaN(range[0]) || isNaN(range[1]) || v < range[0] || v > range[1] || v < 0)
                {
                    return ei._error("uint");
                }
                else
                {
                    return ei._success_rule("uint");
                }
            },

            "regex": function (ei, v, p)
            {
                var pattern = new RegExp(p);

                if (false == pattern.test(v))
                {
                    return ei._error("regex");
                }
                else
                {
                    return ei._success_rule("regex");
                }
            }
        },

        destructor: function ()
        {
            //閲嶇疆浜嬩欢
            this.error = null;
            this.success = null;

            //瑙ｉ櫎瀹炴椂楠岃瘉
            this.input.off("blur");

            delete this;
        }
    };

    $.fn.easyinput = function (options)
    {
        var check = new _easyinput(this, options);

        return check.init();
    };

})(jQuery, window, document);

//easytip
(function ($, window, document, undefined)
{
    var _easytip = function (ele, opt)
    {
        this.parent = ele;
        this.is_show = false;

        if (0 == this.parent.length)
        {
            throw new Error("easytip's is null !");
        }

        this.defaults = {
            left: 0,
            top: 0,
            position: "right",          //top, left, bottom, right
            disappear: "other",       	//self, other, lost-focus, none, N seconds, out
            speed: "fast",
            class: "easy-white",
            arrow: "bottom",          	//top, left, bottom, right 鑷姩锛屾墜鍔ㄩ厤缃棤鏁�
            onshow: null,               //浜嬩欢
            onclose: null,               //浜嬩欢
            hover_show: "false"			//榧犳爣绉诲姩鍒扮粦瀹氱洰鏍囨椂锛屾槸鍚﹁嚜鍔ㄥ嚭鐜�
        };

        this._fun_cache = Object();    //鍝嶅簲鍑芥暟缂撳瓨锛岀敤鏉ヤ繚瀛榮how閲岄潰鑷姩娣诲姞鐨刢lick鍑芥暟锛屼互渚夸簬鍚庨潰鐨剈nbind閽堝鎬х殑涓€涓竴涓垹闄�

        //浠庢帶浠剁殑 data-easytip涓鍙栭厤缃俊鎭�
        // var data = easy_load_options(ele[0].id, "easytip"); // # by galandeo, fix bug
        var data = easy_load_options(ele[0], "easytip");

        this.options = $.extend({}, this.defaults, opt, data);

        this.id = "easytip-div-main-" + ele[0].id;
    };

    _easytip.prototype = {

        init: function ()
        {
            var tip = $("#" + this.id);

            var $this = this;

            //鍚屼竴涓帶浠朵笉浼氬娆″垵濮嬪寲銆�
            if (tip.length == 0)
            {
                $(document.body).append("<div id=\"" + this.id + "\"><div class=\"easytip-text\"></div></div>");

                tip = $("#" + this.id);
                var text = $("#" + this.id + " .easytip-text");

                tip.css({
                    "text-align": "left",
                    "display": "none",
                    "position": "absolute",
                    "z-index": 9000
                });

                text.css({
                    "text-align": "left",
                    "min-width": "120px"
                });

                tip.append("<div class=\"easytip-arrow\"></div>");
                var arrow = $("#" + this.id + " .easytip-arrow");
                arrow.css({
                    "padding": "0",
                    "margin": "0",
                    "width": "0",
                    "height": "0",
                    "position": "absolute",
                    "border": "10px solid"
                });

                if (this.options.hover_show == "true")
                {
                    this.options.disappear = "none";
                    this.options.speed = 1;
                    this.parent.hover(function ()
                    {
                        $this.show();
                    }, function ()
                    {
                        $this.close();
                    });
                }
            }

            return this;
        },

        _size: function ()
        {
            var parent = this.parent;
            var tip = $("#" + this.id);

            if (tip.width() > 300)
            {
                tip.width(300);
            }
        },

        _css: function ()
        {
            var tip = $("#" + this.id);
            var text = $("#" + this.id + " .easytip-text");
            var arrow = $("#" + this.id + " .easytip-arrow");

            text.addClass(this.options.class);

            arrow.css("border-color", "transparent transparent transparent transparent");
            tip.css("box-sizing", "content-box");
        },

        _arrow: function ()
        {
            var tip = $("#" + this.id);
            var text = $("#" + this.id + " .easytip-text");
            var arrow = $("#" + this.id + " .easytip-arrow");

            switch (this.options.arrow)
            {
                case "top":
                    arrow.css({
                        "left": "25px",
                        "top": -arrow.outerHeight(),
                        "border-bottom-color": text.css("borderTopColor")
                    });
                    break;

                case "left":
                    arrow.css({
                        "left": -arrow.outerWidth(),
                        "top": tip.innerHeight() / 2 - arrow.outerHeight() / 2,
                        "border-right-color": text.css("borderTopColor")
                    });
                    break;

                case "bottom":
                    arrow.css({
                        "left": "25px",
                        "top": tip.innerHeight(),
                        "border-top-color": text.css("borderTopColor")
                    });
                    break;

                case "right":
                    arrow.css({
                        "left": tip.outerWidth(),
                        "top": tip.innerHeight() / 2 - arrow.outerHeight() / 2,
                        "border-left-color": text.css("borderTopColor")
                    });
                    break;
            }
        },

        _position: function ()
        {
            var tip = $("#" + this.id);
            var text = $("#" + this.id + " .easytip-text");
            var arrow = $("#" + this.id + " .easytip-arrow");
            var offset = $(this.parent).offset();
            var size = {
                width: $(this.parent).outerWidth(),
                height: $(this.parent).outerHeight()
            };

            switch (this.options.position)
            {
                case "top":

                    //tip.css("left", offset.left - this.padding);
                    tip.css("left", offset.left);
                    tip.css("top", offset.top - tip.outerHeight() - arrow.outerHeight() / 2);
                    this.options.arrow = "bottom";

                    break;

                case "left":

                    tip.css("left", offset.left - tip.outerWidth() - arrow.outerWidth() / 2);
                    tip.css("top", offset.top - (tip.outerHeight() - size.height) / 2);
                    this.options.arrow = "right";

                    break;

                case "bottom":

                    //tip.css("left", offset.left - this.padding);
                    tip.css("left", offset.left);
                    tip.css("top", offset.top + size.height + arrow.outerHeight() / 2);
                    this.options.arrow = "top";

                    break;

                case "right":

                    tip.css("left", offset.left + size.width + arrow.outerWidth() / 2);
                    tip.css("top", offset.top - (tip.outerHeight() - size.height) / 2);
                    this.options.arrow = "left";

                    break;
            }

            var left = parseInt(tip.css("left"));
            var top = parseInt(tip.css("top"));

            tip.css("left", parseInt(this.options.left) + left);
            tip.css("top", parseInt(this.options.top) + top);
        },

        close: function (fn)
        {
            var tip = $("#" + this.id);
            var parent = this.parent;
            var onclose = this.options.onclose;
            this.is_show = false;

            //onclose浜嬩欢
            if (!!onclose)
            {
                onclose(parent, tip[0]);
            }

            tip.fadeOut(this.options.speed, fn);
        },

        _show: function ()
        {
            var tip = $("#" + this.id);
            var text = $("#" + this.id + " .easytip-text");
            var arrow = $("#" + this.id + " .easytip-arrow");
            var speed = this.options.speed;
            var disappear = this.options.disappear;
            var parent = this.parent;
            var $this = this;
            this.is_show = true;

            if (this.options.hover_show == "true")
            {
                tip.show();
                return;
            }

            tip.fadeIn(speed, function ()
            {
                if (!isNaN(disappear))
                {
                    //濡傛灉disappear鏄暟瀛楋紝鍒欏€掕鏃禿isappear姣鍚庢秷澶�
                    setTimeout(function ()
                    {
                        $this.close();

                    }, disappear);
                }
                else if (disappear == "self" || disappear == "other")
                {
                    $(document).bind('click', $this._fun_cache[tip[0].id] = function (e)
                    {
                        if (disappear == "self" && e.target == text[0])
                        {
                            $this.close(function ()
                            {
                                $(document).unbind("click", $this._fun_cache[tip[0].id]);
                            });

                        }
                        else if (disappear == "other" && e.target != tip[0])
                        {
                            $this.close(function ()
                            {
                                $(document).unbind("click", $this._fun_cache[tip[0].id]);
                            });
                        }
                    });
                }
                else if (disappear == "lost-focus")
                {
                    $(parent).focusout(function ()
                    {
                        $this.close(function ()
                        {
                            $(parent).unbind("focusout");
                        });
                    });
                }

            });
        },

        show: function (msg)
        {
            var tip = $("#" + this.id);
            var text = $("#" + this.id + " .easytip-text");
            var arrow = $("#" + this.id + " .easytip-arrow");
            var speed = this.options.speed;
            var disappear = this.options.disappear;
            var parent = this.parent;
            var $this = this;
            var onshow = this.options.onshow;

            if (!msg)
            {
                msg = parent.data("easytip-message");
            }

            text.html(msg);

            this._size();
            this._css();
            this._position();
            this._arrow();

            if ("none" == tip.css("display"))
            {
                //onshow浜嬩欢
                if (!!onshow)
                {
                    onshow(parent, tip[0]);
                }
                $this._show();
            }
            else
            {
                tip.hide(1, function ()
                {
                    if (!!onshow)
                    {
                        onshow(parent, tip[0]);
                    }

                    $this._show();
                });
            }
        }
    };

    $.fn.easytip = function (options)
    {
        var tip = new _easytip(this, options);

        return tip.init();
    };

})(jQuery, window, document);