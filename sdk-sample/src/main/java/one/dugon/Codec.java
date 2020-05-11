package one.dugon;


import java.util.Map;

public class Codec {
    class RtcpFeedback {
        String type = null;
        String parameter = null;
    }

    class Extension {
        Integer id = 0;
        String uri = null;
        Boolean recv = false;
        Boolean send = false;
    }

    class RTX {
        Integer payload = 0;
        Integer ssrc = 0;
    }

    public String kind = null;
    public Integer payload = 0;
    public String codecName = null;
    public String codecFullName = null;
    public Integer clockRate = 0;
    public Integer channels = 0;
    public String mid = null;
    public Integer ssrc = 0;
    public String cname = null;
    public Boolean dtx = false;
    public Boolean reducedSize = false;
    public Boolean senderPaused = false;
    public RTX rtx = null;
    public Extension[] extensions;
    public Map<String, String> parameters = null;
    public RtcpFeedback[] rtcpFeedback = null;

}
