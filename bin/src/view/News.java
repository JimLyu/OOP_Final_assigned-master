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
            "2019/01/11 �x�W���K�w��լd���}���b�Ȯ׻���",
            "2019/01/02 �x�W���K2019�K�`���B �[�}465�Z���C�� 1/4���}���ʲ�",
            "2019/01/01 ��(1)��U�Ȥα߶��[�}2�Z���_�W�U�����a�������ۥѮy�C��",
            "2018/12/31 ��(31)��U�Ȥα߶��[�}2�Z���_�W�U�����a�������ۥѮy�C��",
            "2018/12/28 ����������m12/28, 29 �@�[�}3�Z���n�U�U�����a�������ۥѮy�C��",
            "2018/12/28 �f���K�D�x�V�C �u���K�����p���v�n�d1+1 �u�f�t�����I",
            "2018/12/27 �t�X�x�n������~�߷|���ʽվ��{�����Ȱ�",
            "2018/12/26 ���K�����q���t�ΡBT-EX�B���~�P�C�鲼�ȻPTGo�|���I���M�ϱN��12/27(�|)���2�I��5�I�Ȱ��u�W�I�ڪA��",
            "2018/12/25 �����q���t�ΤΦ۰ʻy���q��t��²�T�q���\��2018/12/27 (�|) 00:30~05:00AM�Ȱ��A��",
            "2018/12/24 �୼�u�f���i"
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
    private Font font = new Font("�ө���",Font.BOLD,FONT_SIZE);
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
