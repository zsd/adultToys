
var WEB_VERSION = '0.2.18.3102';
var HOST_TYPE_PVG = 0;
var HOST_TYPE_VSP = 1;
var HostTypes = ['pvghost', 'vsphost'];
var HostPorts = [2000, 4600];


var HOST_TYPE = HOST_TYPE_PVG;
//~ var HOST_TYPE = HOST_TYPE_VSP;

var g_pixHost = null;
var g_pixStrmM = null;



/*-----------------------------------------------------------------------------
 * pixposa & pixnvr function
 *-----------------------------------------------------------------------------
 */

function login(ip, user, pswd, type)
{
    pixGlob.InsertModulesOfDir('');

    var port = HostPorts[type]
    var host = HostTypes[type];

    //pvgstr="vsphost:192.168.11.147:4601"//url的格式
    var pvgstr = host + ':' + ip + ':' + port + '/username=' + user + '&password=' + pswd;

    if ( g_pixHost != null ) {
        logout();
    }
    var vec = pixGlob.MakeVec();
    vec.Append ('url');
    vec.Append (pvgstr);
    vec.Append('timeout');
    vec.Append('3000');
    g_pixHost = pixGlob.MakeObjectEx('Host', vec);

    if ( g_pixHost == null  ) {
        return '';
    }

    var pbreak=g_pixHost.Cast('BreakableObj')
    var state = pbreak.connectStatus;
    if (state < 0) {
        return '';
    }
    else {
        return pvgstr;
    }
}

function logout()
{
    if ( g_pixHost != null ) {
        g_pixStrmM = null;
        g_pixHost = null;
    }
}

function getCameraList()
{
    var cameras = new Array();
    var objPixContainer =g_pixHost.Cast('ObjContainer');
    var vecInclude = pixGlob.MakeVec();
    var vecExclude = pixGlob.MakeVec();
    vecInclude.Set(0, 0x02);
    var vec = objPixContainer.EnumObj('$', vecInclude, vecExclude)
    for(var i=0; i<=vec.count-1; i=i+6) {
        var cam = new Object();
        cam.id = vec.Get(i);
        cam.text = vec.Get(i+1);
        cam.mask = vec.get(i+2);
        cameras.push(cam);
    }
    return cameras;
};

function startRealplay(pvgstr, name, hwnd)
{
    var tgtstr =  'live:'+hwnd+':vrnd';
    var srcstr = pvgstr + '#' + name + '#0#s/tcp/';

    try {
        if ( g_pixStrmM == null ) {
            var vec =pixGlob.MakeVec()
            vec.Append ('StreamManager');
            vec.Append ('');
            g_pixStrmM=pixGlob.MakeObjectEx('StreamManager', vec);
        }
        g_pixStrmM.Start(srcstr, 1, tgtstr, 1, 0, -1);

        var avfilterable = g_pixStrmM.FetchTarget(tgtstr, 'AVFilterable');
        var videofilter = avfilterable.videofilter;
        var vrender = videofilter.Cast('VideoRender');
        var rectvec = pixGlob.MakeVec();
        rectvec.Append ('0');
        rectvec.Append ('0');
        rectvec.Append ('0');
        rectvec.Append ('0');
        vrender.SetPane(hwnd, hwnd, rectvec);
        return true;
    }
    catch(err) {
        return false;
    }
};

function stopRealplay(hwnd)
{
    if ( g_pixStrmM != null ) {
        var strTarget = 'live:'+hwnd +':vrnd';
        var avfilterable = g_pixStrmM.FetchTarget(strTarget, 'AVFilterable')
        var videofilter = avfilterable.videofilter;
        var vrender = videofilter.Cast('VideoRender');
        g_pixStrmM.Stop (strTarget);
        vrender.DelPane (hwnd);
        //pixwin.InvalidateWnd (hwnd, false);
    }
};

function getRenderBcsh(hwnd)
{
    var bcsh = new Object();
    if (g_pixStrmM != null) {
        var strTarget = 'live:' + hwnd + ':vrnd'
        var avfilterable = g_pixStrmM.FetchTarget(strTarget, 'AVFilterable')
        var videofilter = avfilterable.videofilter
        var vrender = videofilter.Cast('VideoRender')
        var colorVec = vrender.GetColor()
        var i = 0;
        //~ for (var i = 0; i <= colorVec.Count - 1; i += 4) {
        bcsh.brightness = colorVec.Get(i);
        bcsh.contrast = colorVec.Get(i + 1);
        bcsh.saturation = colorVec.Get(i + 2);
        bcsh.hue = colorVec.Get(i + 3);
        //~ }
    }
    return bcsh;
};

function setRenderBcsh(hwnd, brightness, contrast, saturation, hue)
{
    if ( g_pixStrmM != null ) {
        var strTarget = 'live:' + hwnd + ':vrnd';
        var avfilterable = g_pixStrmM.FetchTarget(strTarget, 'AVFilterable');
        var videofilter = avfilterable.videofilter;
        var vrender = videofilter.Cast('VideoRender');
        vrender.SetColor(brightness, contrast, saturation, hue);
    }
};

function getVideoBcsh(avname)
{
    var info = new Object();
    info.error = ERROR_OK;

    try {
        var objPixContainer =g_pixHost.Cast('ObjContainer');
        var avobj = objPixContainer.FetchObj(avname, 'Avobj');

        var colorobj = avobj.Cast('VideoColor');
        var colorVec = colorobj.GetColor()
        var i = 0;
        //~ for (var i = 0; i <= colorVec.Count - 1; i += 4) {
        info.brightness = colorVec.Get(i) - 127;		//亮度范围为0~255，其他为-128~127，统一成-127~127
        info.contrast = colorVec.Get(i + 1);
        info.saturation = colorVec.Get(i + 2);
        info.hue = colorVec.Get(i + 3);
        //~ }
        if ( info.brightness > 127 ) info.brightness = 127;
        if ( info.contrast < -127 ) info.contrast = -127;
        if ( info.saturation < -127 ) info.saturation = -127;
        if ( info.hue < -127 ) info.hue = -127;
    }
    catch(err) {
        info.error = err.message;
    }

    return info;
}

function setVideoBcsh(avname, brightness, contrast, saturation, hue)
{
    try {
        var objPixContainer =g_pixHost.Cast('ObjContainer');
        var avobj = objPixContainer.FetchObj(avname, 'Avobj');

        var colorobj = avobj.Cast('VideoColor');
        colorobj.SetColor(brightness+127, contrast, saturation, hue);
        return ERROR_OK;
    }
    catch(err) {
        return err.message;
    }
}

function ptzCmd(hwnd, cmd, speed)
{
    if (g_pixStrmM != null) {
        var strTarget = 'live:' + hwnd + ':vrnd';
        var plist = g_pixStrmM.List('', strTarget);
        var pstream = g_pixStrmM.FetchSource(plist.Get(0), '');
        var pownObj = pstream.Cast('OwnedObj');	//PixOwnedObj
        var strSrcUrl = plist.Get(0);
        var objOwner = g_pixStrmM.FetchSource(strSrcUrl, 'OwnedObj').FetchOwner('OwnedObj').FetchOwner('OwnedObj');
        var avobj = objOwner.Cast('Avobj');
        var ptzc = avobj.Cast('PtzControl');
        ptzc.Ptz(cmd, speed);
    }
}

function PtzStart(hwnd, cmd, speed)
{
    ptzCmd(hwnd, cmd, speed);
}

function PtzStop(hwnd, cmd)
{
    ptzCmd(hwnd, cmd, 0);
}

function getDevInfo()
{
    var info = new Array();
    info.error = ERROR_OK;
    try {
        var vec = g_pixHost.GetInfo();
        for (var i = 0; i<vec.count; i++ ) {
            if ( i != 2 ) {
                info[i] = vec.Get(i);
            }
            else {
                var vv = vec.Get(i);
                for ( var j=0; j < vv.count; j+=2 ) {
                    info[vv.Get(j)] = vv.Get(j+1);
                }
            }
        }
    }
    catch(err) {
        info.error = err.message;
    }
    return info;
}


function getChanBasicConf (avname)
{
    var info = new Object();
    info.error = ERROR_OK;

    try {
        var objPixContainer =g_pixHost.Cast('ObjContainer');
        var avobj = objPixContainer.FetchObj(avname, 'Avobj');

        var textlay = avobj.Cast('VideoTextOverlay');
        var textvec = textlay.GetText();
        info.textpos = textvec.Get(0);
        info.text = textvec.Get(1);

        var timelay = avobj.Cast('VideoDateTimeOverlay');
        info.timepos = timelay.place;
    }
    catch(err) {
        info.error = err.message;
    }

    return info;
}

function setChanBasicConf(avname, info)
{
    try {
        var objPixContainer =g_pixHost.Cast('ObjContainer');
        var avobj = objPixContainer.FetchObj(avname, 'Avobj');

        var textlay = avobj.Cast('VideoTextOverlay');
        textlay.SetText(info.textpos, info.text);

        var timelay = avobj.Cast('VideoDateTimeOverlay');
        timelay.place = info.timepos;
        return ERROR_OK;
    }
    catch(err) {
        return err.message;
    }
}

function getChanEncConf(avname)
{
    var info = new Array();
    info.error = ERROR_OK;

    try {
        var objPixContainer =g_pixHost.Cast('ObjContainer');
        var avobj = objPixContainer.FetchObj(avname, 'Avobj');

        var encvec = avobj.ListEncoder();
        for (var i=0; i<encvec.count; i+=2) {
            var enc = new Object();
            enc.addr = encvec.Get(i);
            //enc.strmtype = encvec.Get(i+1);
            var chobj = avobj.FetchEncoder( enc.addr, 'EncoderChannel' );
            var encobj = chobj.Cast('VideoEncoder');
            var vec = encobj.GetVideoEncoder();
            enc.type = vec.Get(0);
            enc.framerate = vec.Get(1);
            enc.width = vec.Get(2);
            enc.height = vec.Get(3);
            enc.bitrate = vec.Get(4);
            enc.quality = vec.Get(5);
            enc.keyinterval = vec.Get(6);

            info.push(enc);
        }
    }
    catch (err) {
        info.error = err.message;
    }
    return info;
}

function setChanEncConf(avname, info)
{
    try {
        var objPixContainer =g_pixHost.Cast('ObjContainer');
        var avobj = objPixContainer.FetchObj(avname, 'Avobj');
        var chobj = avobj.FetchEncoder( info.addr, 'EncoderChannel' );
        var encobj = chobj.Cast('VideoEncoder');
        encobj.SetVideoEncoder(info.type, info.framerate, info.width, info.height, info.bitrate, info.quality, info.keyinterval);

        return ERROR_OK;
    }
    catch(err) {
        return err.message;
    }
}
