Page({
  data: {
    swiperCurrent: 0,
    indicatorDots: true,
    autoplay: true,
    interval: 3000,
    duration: 800,
    circular: true,
    imgUrls: [
      'https://img1.doubanio.com/view/photo/l/public/p2463041948.webp',
      'https://img1.doubanio.com/view/photo/l/public/p2463041948.webp',
      'https://img1.doubanio.com/view/photo/l/public/p2463041948.webp'
    ],
    array: [{
      message: 'foo',
      clickUrl: 'https://mp.weixin.qq.com/s/o_f5SXIedZASlRG3m_BnAA'
    }, {
      message: 'bar',
      clickUrl: 'https://mp.weixin.qq.com/s/o_f5SXIedZASlRG3m_BnAA'
    }]

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.request({
      url: 'http://localhost:8080/carousel', //仅为示例，并非真实的接口地址
      success: function (res) {
        console.log(res.data)
        
      }
    })
  },
  //轮播图的切换事件
  swiperChange: function (e) {
    this.setData({
      swiperCurrent: e.detail.current
    })
  },
  //点击指示点切换
  chuangEvent: function (e) {
    this.setData({
      swiperCurrent: e.currentTarget.id
    })
  },
  //点击图片触发事件
  swipclick: function (e) {
    console.log(this.data.swiperCurrent);
    wx.switchTab({
      url: this.data.links[this.data.swiperCurrent]
    })
  },
  imageClick:function (url){
    wx.navigateTo({
      url: '/pages/out/out',
    })
  }
})