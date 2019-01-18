package view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * News
 *
 * @author Jim
 */
public class News extends JPanel implements Runnable {

    private final String[] MSG = {
            "2019/01/11 台灣高鐵針對調查局破獲駭客案說明",
            "2019/01/02 台灣高鐵2019春節疏運 加開465班次列車 1/4凌晨開放購票",
            "2019/01/01 今(1)日下午及晚間加開2班次北上各站停靠之全車自由座列車",
            "2018/12/31 今(31)日下午及晚間加開2班次北上各站停靠之全車自由座列車",
            "2018/12/28 元旦假期返鄉12/28, 29 共加開3班次南下各站停靠之全車自由座列車",
            "2018/12/28 搭高鐵．暖冬遊 「高鐵飯店聯票」好康1+1 優惠配成雙！",
            "2018/12/27 配合台南市府跨年晚會活動調整臨停載客區",
            "2018/12/26 高鐵網路訂票系統、T-EX、海外周遊券票務與TGo會員兌換專區將於12/27(四)凌晨2點至5點暫停線上付款服務",
            "2018/12/25 網路訂票系統及自動語音訂位系統簡訊通知功能2018/12/27 (四) 00:30~05:00AM暫停服務",
            "2018/12/24 轉乘優惠公告"
    };
    private final String[] LINK = {
            "http://www.thsrc.com.tw/tw/News/Detail/9b269962-0469-4813-bb3b-4b641565bdff/1",
            "http://www.thsrc.com.tw/tw/News/Detail/d343fe4c-c8d4-48eb-99a3-d59846f1072c/2",
            "http://www.thsrc.com.tw/tw/News/Detail/db179a4e-d9b3-43fb-af24-94c4be5d69b6/3",
            "http://www.thsrc.com.tw/tw/News/Detail/48fd1bc5-244e-4885-ac1d-a12b11feffcd/4",
            "http://www.thsrc.com.tw/tw/News/Detail/14d2be1f-e3ce-4eaa-8d21-35909a9c1c06/5",
            "http://www.thsrc.com.tw/tw/News/Detail/7509eddf-c698-4852-9261-91b41d25b84c/6",
            "http://www.thsrc.com.tw/tw/News/Detail/107f5b1f-b033-426b-8ddd-b57ab2d015b7/7",
            "http://www.thsrc.com.tw/tw/News/Detail/6cf30103-8e9b-4a6d-b7ba-06994818316a/8",
            "http://www.thsrc.com.tw/tw/News/Detail/030ef07b-c9be-4309-8ded-460556a7d6fd/9",
            "http://www.thsrc.com.tw/tw/News/Detail/f376e8f0-40ed-4607-a92e-b91e5c0c658f/10"
    };
    private final String H1 = "<html><a href=\"";
    private final String H2 = "\">";
    private final String H3 = "</a></html>";


    private final int MSG_COUNT = 10;
    private final int WIDTH = 700;
    private final int HEIGHT = 23;
    private final int FONT_SIZE = 18;

    int index = 0;
    private JLabel text = new JLabel();
    private Font font = new Font("細明體",Font.BOLD,FONT_SIZE);
    private FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
    int x = WIDTH;
    int y = FONT_SIZE;


    public News(){
        super();
        //setBackground(Color.white);
        setBounds(0, 0, WIDTH, HEIGHT);

        layout.setHgap(WIDTH);
        layout.setVgap(0);
        setLayout(layout);

        text.setFont(font);
        text.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(LINK[index]));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(text);
        setVisible(true);
    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(50);
            }catch(InterruptedException e){}
            x -= 4;
            layout.setHgap(x);
            if(x < -MSG[index].length() * FONT_SIZE){
                x = WIDTH;
                index++;
                if(index >= MSG_COUNT)
                    index = 0;
                text.setText(H1 + LINK[index] + H2 + MSG[index] + H3);
            }
            updateUI();
        }
    }

}
