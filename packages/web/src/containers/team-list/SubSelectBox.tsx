import { Search, ListToggle, SubButton } from './SubSelectButtons'
import styles from './teamList.module.css'

const SubSelectBox = ({ children }: { children: React.ReactNode }) => {
  return <div className={styles.subSelecBox}>{children}</div>
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
SubSelectBox.ListToggle = ListToggle

export default SubSelectBox
