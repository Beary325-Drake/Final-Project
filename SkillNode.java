import java.util.ArrayList;
/**
 * a base node that will be used for the skill tree
 */
public class SkillNode 
{
    private boolean isRoot;
    private ArrayList<SkillNode> children;
    private SkillNode parent = null;
    private boolean unlocked;
    private Skill skill;

    public SkillNode(Skill skill)
    {
        this.children = new ArrayList<SkillNode>();
        this.isRoot = true;
        this.skill = skill;
    }

    public void setParent(SkillNode parent)
    {
        this.parent = parent;
        if (parent != null)
        {
            this.isRoot = false;
        }
        else
        {
            this.isRoot = true;
        }
    }

    public SkillNode getParent()
    {
        if (this.isRoot)
        {
            return this;
        }
        else
        {
            return parent;
        }
    }

    public void addChild(SkillNode child)
    {
        children.add(child);
        child.setParent(this);
    }

    public void removeChild(SkillNode child)
    {
        if (children.contains(child))
        {
            children.remove(child);
            child.setParent(null);
        }
    }

    public boolean hasChild(SkillNode child)
    {
        if (children.contains(child))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void setUnlocked(boolean unlocked)
    {
        this.unlocked = unlocked;
        skill.setUnlocked(unlocked);
    }
    
    public boolean getUnlocked()
    {
        return unlocked;
    }

    public Skill getSkill()
    {
        return this.skill;
    }

    public void setSkill(Skill skill)
    {
        this.skill = skill;
    }
    
    public boolean contains(SkillNode child)
    {
        if (children.contains(child))
        {
            return true;
        }
        
        for (SkillNode childNode : children)
        {
            if (childNode.contains(child))
            {
                return true;
            }
        }
        return false;
    }

    public boolean containsSkill(Skill skill)
    {
        for (SkillNode child : children)
        {
            if (child.getSkill() == this.skill)
            {
                return true;
            }
        }
        
        for (SkillNode childNode : children)
        {
            if (childNode.containsSkill(skill))
            {
                return true;
            }
        }
        return false;
    }




    /**
     * determines if this skill is connected to an already learned skill, or is the root skill 
     */
    public boolean canLearn()
    {
        if (getParent() == null)
        {
            return true;
        }    
        else
        {
            return getParent().getUnlocked();
        }    
    }        
}
