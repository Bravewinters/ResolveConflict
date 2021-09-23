```
DOWN:
ViewGroup.java
 dispatchTouchEvent
  onFilterTouchEventForSecurity
  final boolean disallowIntercept = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;	=> false
  final boolean intercepted;
  if (!disallowIntercept) {	//true
        intercepted = onInterceptTouchEvent(ev);	//viewgroup
        ev.setAction(action); // restore action in case it was changed
  } else {
        intercepted = false;
  }

  if (!canceled && !intercepted) //true
  	buildTouchDispatchChildList()
  		1.获取子viewZ值
  		2.按Z值从小到大排列
  	if (!child.canReceivePointerEvents() || !isTransformedTouchPointInView(x, y, child, null))	//能接收事件  当前坐标是否在 子view上
  	dispatchTransformedTouchEvent
  		child.dispatchTouchEvent(transformedEvent);	==> false
	  		if (child == null) {
	            handled = super.dispatchTouchEvent(event);
	        } else {
	             handled = child.dispatchTouchEvent(event);	==> false
	             	1.li.mOnTouchListener.onTouch(this, event)//如果 onTouch返回true  则不会调用 onTouchEvent(event)

	             	2.onTouchEvent(event) => //假设子view 都不处理 ==> false
	        }

	//如果循环完毕都没有子view处理
	mFirstTouchTarget = null;
	dispatchTransformedTouchEvent(ev, canceled, null,TouchTarget.ALL_POINTER_IDS)
		 if (child == null) {
            handled = super.dispatchTouchEvent(transformedEvent);
            	onTouchEvent(event)		==> false  //调用自己的onTouchEvent
        }

父:
dispatchTouchEvent  		 return super.dispatchTouchEvent(ev);
onInterceptTouchEvent  	 return super.onInterceptTouchEvent(ev);
onTouchEvent    				 return super.onTouchEvent(ev);

子:
dispatchTouchEvent  		 return super.dispatchTouchEvent(ev);
onTouchEvent    				 return true;
==============正常的事件传递流程=====================
2021-08-13 16:14:42.077 14610-14610/? I/System.out: dispatchTouchEvent# 父亲  down
2021-08-13 16:14:42.077 14610-14610/? I/System.out: 父亲拦截事件# 父亲  down
2021-08-13 16:14:42.077 14610-14610/? I/System.out: dispatchTouchEvent # 孩子  down
2021-08-13 16:14:42.077 14610-14610/? I/System.out: 孩子 onTouchEvent# 孩子  down
2021-08-13 16:14:42.238 14610-14610/? I/System.out: dispatchTouchEvent# 父亲  move
2021-08-13 16:14:42.238 14610-14610/? I/System.out: 父亲拦截事件# 父亲  move
2021-08-13 16:14:42.238 14610-14610/? I/System.out: dispatchTouchEvent # 孩子  move
2021-08-13 16:14:42.238 14610-14610/? I/System.out: 孩子 onTouchEvent# 孩子  move
2021-08-13 16:14:42.306 14610-14610/? I/System.out: dispatchTouchEvent# 父亲  move
2021-08-13 16:14:42.306 14610-14610/? I/System.out: 父亲拦截事件# 父亲  move
2021-08-13 16:14:42.306 14610-14610/? I/System.out: dispatchTouchEvent # 孩子  move
2021-08-13 16:14:42.306 14610-14610/? I/System.out: 孩子 onTouchEvent# 孩子  move
2021-08-13 16:14:42.406 14610-14610/? I/System.out: dispatchTouchEvent# 父亲  move
2021-08-13 16:14:42.406 14610-14610/? I/System.out: 父亲拦截事件# 父亲  move
2021-08-13 16:14:42.406 14610-14610/? I/System.out: dispatchTouchEvent # 孩子  move
2021-08-13 16:14:42.406 14610-14610/? I/System.out: 孩子 onTouchEvent# 孩子  move
2021-08-13 16:14:42.428 14610-14610/? I/System.out: dispatchTouchEvent# 父亲  up
2021-08-13 16:14:42.428 14610-14610/? I/System.out: 父亲拦截事件# 父亲  up
2021-08-13 16:14:42.428 14610-14610/? I/System.out: dispatchTouchEvent # 孩子  up
2021-08-13 16:14:42.428 14610-14610/? I/System.out: 孩子 onTouchEvent# 孩子  up


父:
dispatchTouchEvent  		 return super.dispatchTouchEvent(ev);
onInterceptTouchEvent  	 return super.onInterceptTouchEvent(ev);
onTouchEvent    				 return super.onTouchEvent(ev);

子:
dispatchTouchEvent  		 return super.dispatchTouchEvent(ev);
onTouchEvent    				 return true;
2021-08-13 15:54:16.118 25062-25062/com.example.resolveconflict I/System.out: dispatchTouchEvent# 父亲  down
2021-08-13 15:54:16.118 25062-25062/com.example.resolveconflict I/System.out: 父亲拦截事件# 父亲  down
2021-08-13 15:54:16.118 25062-25062/com.example.resolveconflict I/System.out: dispatchTouchEvent # 孩子  down
2021-08-13 15:54:16.118 25062-25062/com.example.resolveconflict I/System.out: 孩子 onTouchEvent# 孩子  down
2021-08-13 15:54:16.169 25062-25062/com.example.resolveconflict I/System.out: dispatchTouchEvent# 父亲  move
2021-08-13 15:54:16.169 25062-25062/com.example.resolveconflict I/System.out: 父亲拦截事件# 父亲  move
2021-08-13 15:54:16.169 25062-25062/com.example.resolveconflict I/System.out: dispatchTouchEvent # 孩子  move
2021-08-13 15:54:16.169 25062-25062/com.example.resolveconflict I/System.out: 孩子 onTouchEvent# 孩子  move
2021-08-13 15:54:16.256 25062-25062/com.example.resolveconflict I/System.out: dispatchTouchEvent# 父亲  move
2021-08-13 15:54:16.256 25062-25062/com.example.resolveconflict I/System.out: 父亲拦截事件# 父亲  move
2021-08-13 15:54:16.256 25062-25062/com.example.resolveconflict I/System.out: dispatchTouchEvent # 孩子  move
2021-08-13 15:54:16.256 25062-25062/com.example.resolveconflict I/System.out: 孩子 onTouchEvent# 孩子  move
........
2021-08-13 15:54:16.867 25062-25062/com.example.resolveconflict I/System.out: dispatchTouchEvent# 父亲  up
2021-08-13 15:54:16.867 25062-25062/com.example.resolveconflict I/System.out: 父亲拦截事件# 父亲  up
2021-08-13 15:54:16.868 25062-25062/com.example.resolveconflict I/System.out: dispatchTouchEvent # 孩子  up
2021-08-13 15:54:16.868 25062-25062/com.example.resolveconflict I/System.out: 孩子 onTouchEvent# 孩子  up

结论: 子view的onTouchEvent返回了true 事件就被消费
================================================================================================

父:
dispatchTouchEvent  		 return super.dispatchTouchEvent(ev);
onInterceptTouchEvent  	 return super.onInterceptTouchEvent(ev);
onTouchEvent    				 return super.onTouchEvent(ev);

子:
dispatchTouchEvent  		 return true;
onTouchEvent    				 return true;
2021-08-13 15:59:39.658 12963-12963/com.example.resolveconflict I/System.out: dispatchTouchEvent# 父亲  down
2021-08-13 15:59:39.658 12963-12963/com.example.resolveconflict I/System.out: 父亲拦截事件# 父亲  down
2021-08-13 15:59:39.658 12963-12963/com.example.resolveconflict I/System.out: dispatchTouchEvent # 孩子  down
2021-08-13 15:59:39.761 12963-12963/com.example.resolveconflict I/System.out: dispatchTouchEvent# 父亲  move
2021-08-13 15:59:39.761 12963-12963/com.example.resolveconflict I/System.out: 父亲拦截事件# 父亲  move
2021-08-13 15:59:39.761 12963-12963/com.example.resolveconflict I/System.out: dispatchTouchEvent # 孩子  move
2021-08-13 15:59:39.861 12963-12963/com.example.resolveconflict I/System.out: dispatchTouchEvent# 父亲  move
2021-08-13 15:59:39.861 12963-12963/com.example.resolveconflict I/System.out: 父亲拦截事件# 父亲  move
2021-08-13 15:59:39.861 12963-12963/com.example.resolveconflict I/System.out: dispatchTouchEvent # 孩子  move
2021-08-13 15:59:39.978 12963-12963/com.example.resolveconflict I/System.out: dispatchTouchEvent# 父亲  up
2021-08-13 15:59:39.978 12963-12963/com.example.resolveconflict I/System.out: 父亲拦截事件# 父亲  up
2021-08-13 15:59:39.978 12963-12963/com.example.resolveconflict I/System.out: dispatchTouchEvent # 孩子  up

结论:一旦自己的dispatchTouchEvent 返回了true 自己的onTouchEvent 将接收不到事件

================================================================================================
父:
dispatchTouchEvent  		 return true;
onInterceptTouchEvent  	 return super.onInterceptTouchEvent(ev);
onTouchEvent    				 return true;

子:
dispatchTouchEvent  		 return super.dispatchTouchEvent(ev);
onTouchEvent    				 return super.onTouchEvent(ev);
2021-08-13 16:07:06.755 28310-28310/? I/System.out: dispatchTouchEvent# 父亲  down
2021-08-13 16:07:06.877 28310-28310/? I/System.out: dispatchTouchEvent# 父亲  move
2021-08-13 16:07:06.967 28310-28310/? I/System.out: dispatchTouchEvent# 父亲  move
2021-08-13 16:07:07.028 28310-28310/? I/System.out: dispatchTouchEvent# 父亲  move
2021-08-13 16:07:07.082 28310-28310/? I/System.out: dispatchTouchEvent# 父亲  up

结论:一旦自己的dispatchTouchEvent 返回了true 自己的onTouchEvent 将接收不到事件
================================================================================================

父:
dispatchTouchEvent  		 return super.dispatchTouchEvent(ev);
onInterceptTouchEvent  	 return true/false;
onTouchEvent    				 return true;

子:
dispatchTouchEvent  		 return super.dispatchTouchEvent(ev);
onTouchEvent    				 return super.onTouchEvent(ev);

2021-08-13 16:09:43.005 28308-28308/com.example.resolveconflict I/System.out: dispatchTouchEvent# 父亲  down
2021-08-13 16:09:43.005 28308-28308/com.example.resolveconflict I/System.out: 父亲拦截事件# 父亲  down
2021-08-13 16:09:43.005 28308-28308/com.example.resolveconflict I/System.out: dispatchTouchEvent # 孩子  down
2021-08-13 16:09:43.005 28308-28308/com.example.resolveconflict I/System.out: 孩子 onTouchEvent# 孩子  down
2021-08-13 16:09:43.005 28308-28308/com.example.resolveconflict I/System.out: 父亲 touch 事件# 父亲  down
2021-08-13 16:09:43.113 28308-28308/com.example.resolveconflict I/System.out: dispatchTouchEvent# 父亲  move
2021-08-13 16:09:43.113 28308-28308/com.example.resolveconflict I/System.out: 父亲 touch 事件# 父亲  move
2021-08-13 16:09:43.177 28308-28308/com.example.resolveconflict I/System.out: dispatchTouchEvent# 父亲  move
2021-08-13 16:09:43.177 28308-28308/com.example.resolveconflict I/System.out: 父亲 touch 事件# 父亲  move
2021-08-13 16:09:43.283 28308-28308/com.example.resolveconflict I/System.out: dispatchTouchEvent# 父亲  move
2021-08-13 16:09:43.283 28308-28308/com.example.resolveconflict I/System.out: 父亲 touch 事件# 父亲  move
2021-08-13 16:09:43.301 28308-28308/com.example.resolveconflict I/System.out: dispatchTouchEvent# 父亲  up
2021-08-13 16:09:43.301 28308-28308/com.example.resolveconflict I/System.out: 父亲 touch 事件# 父亲  up

结论:父view的onTouchEvent返回true, 拦截事件都对当前的view不生效



//case1: 从ziView开始滑动 都调用super.dispatchXX
07-16 09:48:43.603 1724-1724/com.example.viewlistview D/ZHANGJUNPU: ------父亲-----dispatchTouchEvent: DOWN
07-16 09:48:43.603 1724-1724/com.example.viewlistview D/ZHANGJUNPU: ------父亲-----onInterceptTouchEvent: DOWN
07-16 09:48:43.603 1724-1724/com.example.viewlistview D/ZHANGJUNPU: ------父亲------onInterceptTouchEvent 返回结果: false
07-16 09:48:43.603 1724-1724/com.example.viewlistview D/ZHANGJUNPU: -------------------儿子--------------dispatchTouchEvent: DOWN
07-16 09:48:43.603 1724-1724/com.example.viewlistview D/ZHANGJUNPU: onTouchEvent: ---------儿子----------dispatchTouchEvent ret: false
07-16 09:48:43.603 1724-1724/com.example.viewlistview D/ZHANGJUNPU: ------父亲-----onTouchEvent: DOWN
07-16 09:48:43.603 1724-1724/com.example.viewlistview D/ZHANGJUNPU:  ------父亲-----onTouchEvent 返回结果: false
07-16 09:48:43.603 1724-1724/com.example.viewlistview D/ZHANGJUNPU:  ------父亲-----dispatchTouchEvent 返回结果: false

//case2: 从子View开始点击滑动 但是父view onInterceptTouch 返回true
07-16 09:51:21.594 1838-1838/com.example.viewlistview D/ZHANGJUNPU: ------父亲-----dispatchTouchEvent: DOWN
07-16 09:51:21.594 1838-1838/com.example.viewlistview D/ZHANGJUNPU: ------父亲-----onInterceptTouchEvent: DOWN
07-16 09:51:21.594 1838-1838/com.example.viewlistview D/ZHANGJUNPU: ------父亲------onInterceptTouchEvent 返回结果: true
07-16 09:51:21.594 1838-1838/com.example.viewlistview D/ZHANGJUNPU: ------父亲-----onTouchEvent: DOWN
07-16 09:51:21.594 1838-1838/com.example.viewlistview D/ZHANGJUNPU:  ------父亲-----onTouchEvent 返回结果: false
07-16 09:51:21.594 1838-1838/com.example.viewlistview D/ZHANGJUNPU:  ------父亲-----dispatchTouchEvent 返回结果: false

//case3: 子view点击滑动  父view dispatchTouchEvent 返回 true;  如果父view dispatch 返回ture 子view得不到处理 以及 父veiw的拦截事件
07-16 09:59:12.779 2201-2201/com.example.viewlistview D/ZHANGJUNPU: ------父亲-----dispatchTouchEvent: DOWN
07-16 09:59:12.779 2201-2201/com.example.viewlistview D/ZHANGJUNPU:  ------父亲-----dispatchTouchEvent 返回结果: true
07-16 09:59:12.805 2201-2201/com.example.viewlistview D/ZHANGJUNPU: ------父亲-----dispatchTouchEvent: MOVE
07-16 09:59:12.805 2201-2201/com.example.viewlistview D/ZHANGJUNPU:  ------父亲-----dispatchTouchEvent 返回结果: true
07-16 09:59:13.423 2201-2201/com.example.viewlistview D/ZHANGJUNPU: ------父亲-----dispatchTouchEvent: MOVE
07-16 09:59:13.423 2201-2201/com.example.viewlistview D/ZHANGJUNPU:  ------父亲-----dispatchTouchEvent 返回结果: true
07-16 09:59:13.683 2201-2201/com.example.viewlistview D/ZHANGJUNPU: ------父亲-----dispatchTouchEvent: UP
07-16 09:59:13.683 2201-2201/com.example.viewlistview D/ZHANGJUNPU:  ------父亲-----dispatchTouchEvent 返回结果: true



//case4: 子view点击滑动  父 dispatch -> false   子 dispatch --> true
07-16 10:10:29.216 2577-2577/com.example.viewlistview D/ZHANGJUNPU: ------父亲-----dispatchTouchEvent: DOWN
07-16 10:10:29.216 2577-2577/com.example.viewlistview D/ZHANGJUNPU: ------父亲-----onInterceptTouchEvent: DOWN
07-16 10:10:29.216 2577-2577/com.example.viewlistview D/ZHANGJUNPU: ------父亲------onInterceptTouchEvent 返回结果: false
07-16 10:10:29.216 2577-2577/com.example.viewlistview D/ZHANGJUNPU: -------------------儿子--------------dispatchTouchEvent: DOWN
07-16 10:10:29.216 2577-2577/com.example.viewlistview D/ZHANGJUNPU: onTouchEvent: ---------儿子----------dispatchTouchEvent ret: false
07-16 10:10:29.216 2577-2577/com.example.viewlistview D/ZHANGJUNPU:  ------父亲-----dispatchTouchEvent 返回结果: true


```
