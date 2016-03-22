package com.test.tonychuang.chat.keyboard;

/**表情栏顶部按钮的监听器
 * Created by TonyChuang on 2016/3/15.
 */
public interface OnOperationListener {
    void send(String content);

    void selectedFace(Faceicon content);

    void selectedEmoji(Emojicon content);

    void selectedBackSpace(Emojicon back);

    void selectedFunction(int index);
}
