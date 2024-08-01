import { Search, ViewToggle, SubButton } from './SubSelectButtons'

import styles from './teamList.module.css'

const SubSelectBox = ({ children }: { children: React.ReactNode }) => {
  return (
    <div className="flex items-center justify-between w-full gap-2">
      {children}
    </div>
  )
}

const Title = ({ title, desc }: { title: string; desc?: string }) => {
  return (
    <div className="flex items-center gap-[17px]">
      <h3 className={styles.subTitle}>{title}</h3>
      <p className={styles.subDesc}>{desc}</p>
    </div>
  )
}

SubSelectBox.Title = Title
SubSelectBox.Search = Search
SubSelectBox.SubButton = SubButton
SubSelectBox.ViewToggle = ViewToggle

export default SubSelectBox
