    var dataEgg ={
        'time':{
            '0':['2015-12-27 00:00:00','2015-12-29 00:00:00'],
            '1':['2015-12-29 00:00:00','2015-12-31 00:00:00'],
            '2':['2015-12-31 00:00:00','2016-1-2 00:00:00'],
            '3':['2016-1-2 00:00:00','2016-1-4 00:00:00']
        },
        'hero':['Zed','Sona','Garen',''],
        'video':['r0178zbnnw9','g0178yqw0w5','q0178y9qd4q','']
    };
    function timeTransform(times){
        var timeStr = times.replace('-',' ').replace('-',' ').split(' ');
        return new Date(timeStr[0]+'/'+timeStr[1]+'/'+timeStr[2]+' '+timeStr[3]).getTime();
    }
    for(var t in dataEgg['time']){
        for(var j = 0;j<2;j++){
            dataEgg['time'][t][j] = timeTransform(dataEgg['time'][t][j]);
        }
    }
    var nowtime = timeTransform(json_curdate);
    for(var m in dataEgg['time']){
        if(nowtime > dataEgg['time'][m][0] && nowtime < dataEgg['time'][m][1]){
            if(((/id=([a-zA-Z]+)/ig).exec(window.location.search)[1] == dataEgg['hero'][m]) && dataEgg['video'][m] != ''){
                showEgg(m);
            }
        }
    }

    function showEgg(o){
        g('EggVideo').style.display = 'block';
        var video = new tvp.VideoInfo();
        video.setVid(dataEgg['video'][o]);
        var player =new tvp.Player();
        player.create({
            width:'100%',
            height:'100%',
            video:video,
            modId:"EggVideoContent", //mod_player是刚刚在页面添加的div容器
            autoplay:true,
            vodFlashSkin:'http://imgcache.qq.com/minivideo_v1/vd/res/skins/TencentPlayerMiniSkin.swf',
            flashWmode:"opaque",
            onallended:function(){
                g('EggVideo').innerHTML='';
                g('EggVideo').style.display = 'none';
            }
        });
    }

//视频
var insertFlash = function (elm,eleid,url,w,h){if(!g(elm))return;var str='';str+='<object width="'+w+'" height="'+h+'" id="'+eleid+'" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=10,0,0,0">';str+='<param name="movie" value="'+url+'" />';str+='<param value="true" name="allowFullScreen">';str+='<param name="allowScriptAccess" value="always" />';str+='<param name="wmode" value="transparent" />';str+='<param name="quality" value="autohigh" />';str+='<embed width="'+w+'" height="'+h+'" name="'+eleid+'" src="'+url+'" quality="autohigh" swLiveConnect="always" wmode="transparent" allowFullScreen="true" allowScriptAccess="always" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer"></embed>';str+='</object>';g(elm).innerHTML=str},
pcPlayer = function (u){insertFlash("VideoContent","v",u,610,376)},
showVideos = function(strVideoUrl){
	if (strVideoUrl.indexOf("qq.com") != -1 && strVideoUrl.indexOf("=") != -1) {
            strVideoId = strVideoUrl.split("?")[1].split("vid=")[1].split("&")[0];
            var video = new tvp.VideoInfo(),player;
            video.setVid(strVideoId);
            player = new tvp.Player(610, 376);
            player.setCurVideo(video);
			player.addParam("flashskin","http://imgcache.qq.com/minivideo_v1/vd/res/skins/TencentPlayerMiniSkin.swf")
			player.addParam("extvars",{showend:0,share:0,light:0,popup:0,showlogo:0,favorite:0,searchbar:0,searchpanel:0})
            player.write("VideoContent");
        } else if (strVideoUrl.indexOf("youku.com") != -1) {
            var ua = navigator.userAgent.toLowerCase();
            if (ua.indexOf('ipad') > 0 || ua.indexOf('iphone') > 0 || ua.indexOf('ipod') > 0) {
                var regxs = ['http://\\w+\.youku\.com/player\.php/sid/(\\w+)/v\.swf', 'http://\\w+\.youku\.com/.*VideoIDS=(\\w+)'],result=[];
				for(var i=0;i<2;i++){
					result = new RegExp(regxs[i], 'ig').exec(strVideoUrl);
					if(result!=null) break;
				}
                if (result) {
                    g("VideoContent").innerHTML = '<video id="yk-html5-play" width="610" height="376" controls autoplay 
                    src="http://v.youku.com/player/getRealM3U8/vid/' + result[1] + '/type//video.m3u8"></video>';
                }
            } else {
                pcPlayer(strVideoUrl)
            }
        } else {
            pcPlayer(strVideoUrl)
        }
},
hidVod = function(){g("defailVodPlayer").style.display="none";},
showVideosDialogs = function(key){
	var selfdata = LOLherojs.otherthings.data[key];
	if(selfdata.buylink!=""){
		var lnk =  selfdata.buylink.split("/")[5].split(".shtml")[0];
		g("defailBuy").href = selfdata.buylink+"?ADTAG=cop.innercop.lol_guanwang.hero_"+lnk;
		g("defailBuy").onclick = function(){pgvSendClick({hottag:'infodefail.link.buy.'+key})};
	}else{
		g("defailBuy").href = "javascript:alert('暂未开启，敬请期待哦！');";
		g("defailBuy").target = "_self";
	}
	g("defailVideo").onclick = function(){
		if(selfdata.vodlink!=""){
			showVideos(selfdata.vodlink);
			g("defailVodPlayer").style.display="block";
			move("defailVodPlayer",1,"-305","left");
			g("defailBuy").onclick = function(){pgvSendClick({hottag:'infodefail.link.video.'+key})};
		}else{
			alert('近期会开放视频，敬请期待哦！');
		}
			
	}
},
//end
//皮肤
showSkin = function(){
	var skin =LOLherojs.champion[heroid].data.skins,
	ids = null,
	names = null,
	nav = [],
	bg = [],
	title = [],
	url='http://ossweb-img.qq.com/images/lol/web201310/skin/';
	for(var i=0,len=skin.length;i<len;i++){
		ids = skin[i].id;
		names = skin[i].name;
		if(names.indexOf('default')!=-1){names="默认皮肤"};
		// loadScript(url+'small'+ids+'.jpg',function(){console.log(0);});
		nav.push('<li rel="_'+i+'"><a title="'+names+'"><img width="60" height="60" src="'+url+'small'+ids+'.jpg" alt="'+names+'"><span class="sbor"></span></a></li>');
		bg.push('<li title="'+names+'"><img src="'+url+'big'+ids+'.jpg" alt="'+names+'"></li>');
		title.push(names);
	}
	g('skinNAV').innerHTML = nav.join('');
	g('skinBG').innerHTML = bg[0];
	g('skinName').innerHTML = title[0];
	var sw = gets.tag('skinNAV',"li"),ord=0;
	sw[0].className="current";
	for(var j=sw.length;j--;){
		sw[j].count = j;
		sw[j].onclick = function(){
			var simg = gets.tag('skinBG',"li");
			if(!simg[this.count]){
				g('skinBG').innerHTML = bg.join('');
			}
			move("skinBG",this.count,980,"left");
			sw[ord].className="";
			this.className="current";
			g('skinName').innerHTML = title[this.count];
			ord = this.count;
		}
	}
},
//end
//页面内容
showInfo = {
	"Top":function(heroid){
		//topinfo
		var data =LOLherojs.champion[heroid].data,
		tg=[],
		tmp,
		inf=[];
		g("DATAnametitle").innerHTML = data.name+' '+data.title;
		g("DATAname").innerHTML = data.name;
		g("DATAtitle").innerHTML = data.title;
		for (var i in data.tags) {
			tmp = data.tags[i];
			tg.push('<span>'+getTag(tmp)+'</span>');
		};
		g("DATAtags").innerHTML = tg.join('');
		inf.push('<dt>物理攻击</dt><dd><i class="up up1" title="'+data.info.attack+'" style="width:'+data.info.attack+'0%"></i></dd>');
		inf.push('<dt>魔法攻击</dt><dd><i class="up up2" title="'+data.info.magic+'" style="width:'+data.info.magic+'0%"></i></dd>');
		inf.push('<dt>防御能力</dt><dd><i class="up up3" title="'+data.info.defense+'" style="width:'+data.info.defense+'0%"></i></dd>');
		inf.push('<dt>上手难度</dt><dd><i class="up up4" title="'+data.info.difficulty+'" style="width:'+data.info.difficulty+'0%"></i></dd>');
		g("DATAinfo").innerHTML=inf.join('');
		loadScript("js/herovideo.js",function(){showVideosDialogs(heroid);});
	},
	"BG":function(heroid){
		//背景故事
		var data =LOLherojs.champion[heroid].data;
		g("DATAlore").innerHTML = data.blurb;
		g("Hmore").style.display="none";
		g("Gmore").onclick=function(){
			g("DATAlore").innerHTML = data.lore;
			g("Gmore").style.display="none";
			g("Hmore").style.display="inline";
		};
		g("Hmore").onclick=function(){
			g("DATAlore").innerHTML = data.blurb;
			g("Gmore").style.display="inline";
			g("Hmore").style.display="none";
		};
	},
	"Spell":function(heroid){
		//技能介绍
		var data =LOLherojs.champion[heroid].data,
		stab=[],
		scont=[],
		spe=data.spells,
		len=spe.length,
		lv=[],
		url,
		skey=["Q","W","E","R"],
		url2=gets.u+data.passive.image.group+"/"+data.passive.image.full;
		stab.push('<li class="current"><img src="'+url2+'" alt=""></li>');
		scont.push('<div class="skilltitle"><h5>'+data.passive.name+'</h5><em>被动技能</em></div><p class="skilltip">'+data.passive.description+'</p>');
		for (var i = 0,sp; i < len; i++) {
			sp=spe[i];
			url = gets.u + sp.image.group+"/"+ sp.image.full;
			stab.push('<li><img src="'+url+'" alt=""></li>');
			for (var k = 0, lvlen=sp.leveltip.label.length; k < lvlen; k++) {
				lv.push('<li>'+sp.leveltip.label[k]+'：'+sp.leveltip.effect[k]+'</li>');
			}
            if(sp.leveltip.label.length == 1 && sp.leveltip.label[0] == ''){
                scont.push('<div class="skilltitle"><h5>'+sp.name+'</h5><em>快捷键：'+skey[i]+'</em></div><p class="skilltip">'+sp.tooltip+'</p>')
            }else{
                scont.push('<div class="skilltitle"><h5>'+sp.name+'</h5><em>快捷键：'+skey[i]+'</em></div><p class="skilltip">'+sp.tooltip+'</p><ul class="skillstat">'+lv.join('')+'</ul>')
            }
			lv=[];
		};
		g("DATAspellsNAV").innerHTML = stab.join('');
		g("DATAspells").innerHTML = scont[0];
		var sw=gets.tag("DATAspellsNAV","li"),ord=0;
		for (i = 0; i < len+1; i++) {
			sw[i].count = i;
			sw[i].onclick=function(){
				sw[ord].className="";
				this.className="current";
				g("DATAspells").innerHTML = scont[this.count];
				ord = this.count;
			}
		}
	},
	"Item":function(heroid){
		//推荐装备
		var data =LOLherojs.champion[heroid].data,
		itemsHtml=[],ddHtml=[],type={"starting":"起始装备","essential":"核心物品","offensive":"进攻型物品","defensive":"防御型物品"},typeN=[{"starting":false,"essential":false,"offensive":false,"defensive":false},{"starting":false,"essential":false,"offensive":false,"defensive":false}],typeNum= 0,
		blocks = data.blocks;

		for (var i = 0,lent = blocks.length; i < lent; i++) {
			if((blocks[i].map=="1"&&blocks[i].mode=="CLASSIC")||(blocks[i].map=="12"&&blocks[i].mode=="ARAM")){
                if(blocks[i].map=="1"&&blocks[i].mode=="CLASSIC"){ typeNum = 0;  }
                if(blocks[i].map=="12"&&blocks[i].mode=="ARAM"){ typeNum = 1;  }
                var classbk = blocks[i].recommended,
				itemURL=gets.u+"item/",
				items,
				itemID;
				for(var j=0,ln=classbk.length-1; j<ln; j++){
					items = classbk[j].items;
                    if(classbk[j].type == 'starting' || classbk[j].type == 'essential' || classbk[j].type == 'offensive'  || classbk[j].type == 'defensive' ){
                        if(!typeN[typeNum][classbk[j].type]){
                            for(var t=0,l=items.length;t<l;t++){
                                itemID=items[t].id;
                                itemsHtml.push('<img data-title="'+itemID+'" src="'+itemURL+itemID+'.png" alt="">');
                            }
                            ddHtml.push('<dt>'+type[classbk[j].type]+'</dt><dd class="borlineX">'+itemsHtml.join('')+'</dd>');
                            itemsHtml=[];
                        }
                        typeN[typeNum][classbk[j].type]=true;
                    }
				}
				if(blocks[i].map=="1"){
					g("infospellsTABclassBlocks").innerHTML=ddHtml.join('');
				}else{
					g("infospellsTABaramBlocks").innerHTML=ddHtml.join('');
				}
				ddHtml=[];
			}
		}
		var nobor = gets.tag("jSearchItemDiv","dd");
		nobor[nobor.length-1].className="";
		nobor[3].className="";
		JSwrap("infospellsTAB","li",false,false,"click");
		loadScript("http://lol.qq.com/biz/hero/item.js",function(){showPopup("jSearchItemDiv","img");});
	},
	"Skill":function(heroid){
		//使用技巧
		var data =LOLherojs.champion[heroid].data,
		atips =data.allytips,
		etips=data.enemytips,
		aTmp=[];
		for (var i = 0,l=atips.length; i < l; i++) {
			aTmp.push('<p>'+atips[i]+'</p>')
		}
		g("DATAallytips").innerHTML='<dt>当你使用'+data.name+'</dt><dd>'+aTmp.join('')+'</dd>';
		aTmp=[];
		for (var i = 0,l=etips.length; i < l; i++) {
			aTmp.push('<p>'+etips[i]+'</p>')
		}
		g("DATAenemytips").innerHTML='<dt>敌人使用'+data.name+'</dt><dd>'+aTmp.join('')+'</dd>';
	}
};
//运行
var heroid = encodeReg(GetUrlParamByName("id"));

if(!JisLetter(heroid)){
   location.href="/web201310/info-heros.shtml";
}else{
g("DATAname").innerHTML = GetUrlParamByName("id");
loadScript("http://lol.qq.com/biz/hero/"+heroid+".js",function(){
	if(LOLherojs.champion[heroid]!=null){
		showInfo.Top(heroid);
	}
});
need(["biz.delayLoad"]);
}